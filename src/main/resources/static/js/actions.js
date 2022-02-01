const cardDeck = document.querySelector('.card-deck');
const textAreaContainer = document.querySelector('.textarea-container');


let activeId = 0;
let activeNote = '';
let activeTags = [];
let creatingTag = false;

const getAllNotes = () => {
    cardDeck.innerHTML = '';
    axios.get('/notes/all')
        .then(res => {
            res.data.forEach(ele => {
                var truncString = ele.body.substring(0, 140);
                const newSec = document.createElement('div');
                newSec.classList.add('card');
                cardDeck.appendChild(newSec);
                newSec.innerHTML = 
                `
                    <div class="card-body edit-btn" data-bs-toggle="modal" data-bs-target="#exampleModal" id=${ele.id} onclick="editNote(this)">
                        <p class="card-text">${truncString}</p>
                    </div>
                    <div class="card-footer">
                        <p class="tag-container"></p>
                    </div>
                `
                ele.tags.forEach(tag => {
                    const newTag = document.createElement('span');
                    const tagContainer = newSec.querySelector('.tag-container');
                    newTag.classList.add('w3-tag');
                    newTag.classList.add('w3-blue');
                    newTag.innerText = tag;
                    tagContainer.appendChild(newTag);
                })
            })
        })
}

const editNote = (ele) => {
    let noteId = Number.parseInt(ele.id)
    axios.get(`notes/${noteId}`)
        .then(res => {
            const currentTextArea = document.getElementById('formControlTextarea1');
            currentTextArea.remove();
            const newTextArea = document.createElement('textarea');
            newTextArea.classList.add('form-control');
            newTextArea.id = "formControlTextarea1"
            newTextArea.value = res.data.body;
            textAreaContainer.appendChild(newTextArea);
            activeNote = newTextArea;
            activeId = noteId;
            
            const tagContainer = document.querySelector('.modal-tags');
            tagContainer.innerHTML = '';
            res.data.tags.forEach(tag => {
                activeTags.push(tag);
                const newTag = document.createElement('span');
                newTag.classList.add('w3-tag');
                newTag.classList.add('w3-blue');
                tagContainer.appendChild(newTag);
                newTag.innerHTML = `${tag}<button type="button" class="btn-close btn-sm" aria-label="Close" onclick="removeTag(this)"></button>`
            })
        }) 
}

const saveNote = () => {
    console.log(activeNote);
    let noteBody = activeNote.value;
    let tagsFormatted = String(activeTags);
    const body = {
        body: noteBody,
        tagValues: tagsFormatted,
        archived: "false"
    }
    console.log(body);
    axios.put(`notes/${activeId}`, body)
        .then(res => {
            console.log(res.data);
            getAllNotes();
        })
    activeId = 0;
    activeTags = [];
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
    <span class="w3-tag w3-blue new-tag">
        <input type="text" class="form-control tag-input" id="formGroupExampleInput">
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
        newTag.classList.add('w3-blue');
        tagContainer.appendChild(newTag);
        newTag.innerHTML = `${tagInput}<button type="button" class="btn-close btn-sm" aria-label="Close" onclick="removeTag(this)"></button>`
    }
    inputEle.parentNode.parentNode.innerHTML = `<span class="w3-tag w3-blue new-tag" onclick="newTag(this)"> + </span>`;
    creatingTag = false;
}

const newNote = () => {
    console.log("newNote hit")
}

//This is a catch all function for newly created HTML elements
let newTargets = event => {
    const ele = event.target;
}

let keyListener = event => {
    if (activeId > 0 && event.key === 'Escape') {
        saveNote();
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
