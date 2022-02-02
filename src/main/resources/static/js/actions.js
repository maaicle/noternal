const cardDeck = document.querySelector('.card-deck');
const textAreaContainer = document.querySelector('.textarea-container');


let activeId = 0;
let activeNote = '';
let activeTags = [];
let creatingTag = false;
let isArchived = false;

const getAllNotes = () => {
    cardDeck.innerHTML = '';
    axios.get('/notes/all')
        .then(res => {
            viewNotes(res);
        })
}



const getSearchedNotes = (ele) => {
    let search = ele.querySelector(".search-bar").value;
    
    if (!search) {
        getAllNotes();
    } else {
        console.log("hit getSearchedNotes");
        console.log(search);
        
        cardDeck.innerHTML = '';
        axios.get(`/notes/search/${search}`)
        .then(res => {
            viewNotes(res);
            document.querySelector(".search-bar").value = "";
        })
    }

    // search = "";
}

const viewNotes = (res) => {
    res.data.forEach(ele => {

        // console.log(ele.body);
        var truncString = ele.body.substring(0, 140);
        const newSec = document.createElement('div');
        newSec.classList.add('card');
        newSec.classList.add('mt-4');
        newSec.classList.add('ms-4');
        newSec.classList.add('bg-secondary');
        cardDeck.appendChild(newSec);

        // console.log(truncString)

        newSec.innerHTML = 
        `
            <div class="card-body edit-btn bg-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal" id=${ele.id} onclick="editNote(this)">
                <p class="card-text">${truncString}</p>
            </div>
            <div class="card-footer bg-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal" id=${ele.id} onclick="editNote(this)">
                <p class="tag-container"></p>
            </div>
        `
        ele.tags.forEach(tag => {
            const newTag = document.createElement('span');
            const tagContainer = newSec.querySelector('.tag-container');
            newTag.classList.add('w3-tag');
            newTag.classList.add('w3-white');
            newTag.innerText = tag;
            tagContainer.appendChild(newTag);
        })
    })
}

const newNote = () => {
    const newTextArea = createTextArea();
    activeNote = newTextArea;
    const tagContainer = document.querySelector('.modal-tags');
    tagContainer.innerHTML = '';
    let menuBtn = document.querySelector('.dropdown');
    if (menuBtn) {
        menuBtn.remove();
    }
    setTimeout(() => {
        newTextArea.focus();
    }, 500);
}


const editNote = (ele) => {
    console.log("hit editNote");
    let noteId = Number.parseInt(ele.id)
    axios.get(`notes/${noteId}`)
        .then(res => {
            const newTextArea = createTextArea();
            newTextArea.value = res.data.body;
            activeNote = newTextArea;
            activeId = noteId;

            const moreBtn = document.querySelector(".dropdown-container");
            moreBtn.innerHTML =
            `
            <div class="dropdown">
                <button type="button" class="btn btn-light" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
                        <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
                    </svg>
                </button>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                    <li><a class="dropdown-item btn-warning" href="#" onclick="deleteNote()">Delete</a></li>
                </ul>
            </div>
            `
            const tagContainer = document.querySelector('.modal-tags');
            tagContainer.innerHTML = '';
            res.data.tags.forEach(tag => {
                activeTags.push(tag);
                const newTag = document.createElement('span');
                newTag.classList.add('w3-tag');
                newTag.classList.add('w3-white');
                tagContainer.appendChild(newTag);
                newTag.innerHTML = `${tag}<button type="button" class="btn-close btn-sm lp-1" aria-label="Close" onclick="removeTag(this)"></button>`
            })
        }) 
}

const deleteNote = () => {
    console.log("deleteNote hit");
    isArchived = true;
    // saveNote();
    document.querySelector('.btn-close').click();

}

const saveNote = () => {
    console.log(activeNote);
    let noteBody = activeNote.value;
    let tagsFormatted = String(activeTags);
    const body = {
        body: noteBody,
        tagValues: tagsFormatted,
        archived: isArchived
    }
    console.log(body);

    if(activeId) {
        axios.put(`notes/${activeId}`, body)
        .then(res => {
            console.log(res.data);
            getAllNotes();
        })
    } else {
        if (noteBody || tagsFormatted) {
            axios.post(`notes/`, body)
            .then(res => {
                console.log(res.data);
                getAllNotes();
            })
        }
    }
    activeId = 0;
    activeTags = [];
    activeNote = '';
    isArchived = false;
}

const removeTag = (ele) => {
    console.log(activeTags)
    let removeIndex = activeTags.indexOf(ele.parentNode.innerText)
    if (removeIndex >= 0) {
        activeTags.splice(removeIndex, 1);
    }
    ele.parentNode.remove();
    console.log(activeTags);
}

const newTag = (ele) => {
    console.log("newTag");
    ele.parentNode.innerHTML =
    `
    <span class="new-tag">
        <input type="text" class="form-control tag-input opacity-90 h-50 form-control-sm" id="formGroupExampleInput">
    </span>
    `
    document.querySelector('.tag-input').focus();
    creatingTag = true;
}

const addTag = () => {
    const inputEle = document.querySelector(".tag-input");
    const tagInput = inputEle.value.trim();
    const tagIndex = activeTags.indexOf(tagInput);
    if (tagInput && tagIndex < 0) {
        const tagContainer = document.querySelector('.modal-tags');
        activeTags.push(tagInput);
        const newTag = document.createElement('span');
        newTag.classList.add('w3-tag');
        newTag.classList.add('w3-white');
        tagContainer.appendChild(newTag);
        newTag.innerHTML = `${tagInput}<button type="button" class="btn-close btn-sm lp-1" aria-label="Close" onclick="removeTag(this)"></button>`
    }
    inputEle.parentNode.parentNode.innerHTML = `<span class="w3-tag w3-white new-tag" onclick="newTag(this)"> + </span>`;
    creatingTag = false;
}

const createTextArea = () => {
    const currentTextArea = document.getElementById('formControlTextarea1');
    currentTextArea.remove();
    const newTextArea = document.createElement('textarea');
    newTextArea.classList.add('form-control');
    newTextArea.classList.add('text-white');
    newTextArea.classList.add('bg-dark');
    newTextArea.id = "formControlTextarea1"
    textAreaContainer.appendChild(newTextArea);
    return newTextArea;
}

const logout = () => {
    axios.post("/logout")
        .then(res => {
            console.log(res);
        })
}

//This is a catch all function for newly created HTML elements
let newTargets = event => {
    const ele = event.target;
}

let keyListener = event => {
    if (activeNote && event.key === 'Escape') {
        document.querySelector('.btn-close').click();
    }

    if (creatingTag && (event.key === 'Enter' || event.key === ' ')) {
        addTag();
    }
}


//Listens anytime anything is clicked in the document. newTargets function deciphers the target.
document.addEventListener('click', newTargets);
document.addEventListener('keyup', keyListener);
document.addEventListener('DOMContentLoaded', (event) => {
    getAllNotes();
})
