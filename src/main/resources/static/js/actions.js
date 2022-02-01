const cardDeck = document.querySelector('.card-deck');
const textAreaContainer = document.querySelector('.textarea-container');


let activeId = 0;
let activeNote = '';

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
                console.log(ele.tags);
                ele.tags.forEach(tag => {
                    const newTag = document.createElement('span');
                    const tagContainer = newSec.querySelector('.tag-container');
                    newTag.classList.add('w3-tag');
                    newTag.classList.add('w3-blue');
                    tagContainer.appendChild(newTag);
                    newTag.innerHTML = `${tag} <button type="button" class="btn-close btn-sm" aria-label="Close"></button>`
                })
            // <span class="w3-tag w3-blue">
            //     lil
            // <button type="button" class="btn-close btn-sm" aria-label="Close"></button>
            // </span>
            // <span class="w3-tag w3-blue">
            //     medium
            //     <button type="button" class="btn-close btn-sm" aria-label="Close"></button>
            // </span>
            // <span class="w3-tag w3-blue">
            //     y
            //     <button type="button" class="btn-close btn-sm" aria-label="Close"></button>
            // </span>
                //<button type="button" class="btn btn-primary edit-btn" data-bs-toggle="modal" data-bs-target="#exampleModal" id=${ele.id}>
            })
        })
}

const editNote = (ele) => {
    let noteId = Number.parseInt(ele.id)
    console.log(`opened note's noteId ${noteId}`);
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
            
            res.data.tags.forEach(tag => {
                const newTag = document.createElement('span');
                const tagContainer = document.querySelector('.modal-tags');
                newTag.classList.add('w3-tag');
                newTag.classList.add('w3-blue');
                tagContainer.appendChild(newTag);
                newTag.innerHTML = `${tag} <button type="button" class="btn-close btn-sm" aria-label="Close"></button>`
            })
        }) 
}

const saveNote = () => {
    console.log(activeNote);
    let noteBody = activeNote.value;
    const body = {
        body: noteBody,
        tagValues: "fantasy,mystery,other,other",
        archived: "false"
    }
    console.log(body);
    axios.put(`notes/${activeId}`, body)
        .then(res => {
            console.log(res.data);
            getAllNotes();
        })
    activeId = 0;
}

//<textarea class="form-control" id="formControlTextarea1" row="3" placeholder="this is a placeholder"></textarea>

//This is a catch all function for newly created HTML elements
let newTargets = event => {
    const ele = event.target;
}

let keyListener = event => {
    if (activeId > 0 && event.key === 'Escape') {
        // const closeButton = document.querySelector('btn-close'); 
        saveNote();
    }
}


//Listens anytime anything is clicked in the document. newTargets function deciphers the target.
document.addEventListener('click', newTargets);
document.addEventListener('keyup', keyListener);
document.addEventListener('DOMContentLoaded', (event) => {
    getAllNotes();
})
