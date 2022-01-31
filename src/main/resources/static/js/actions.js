let listBox = document.querySelector('.list-box');

const demo = () => {
    axios.get('/notes/1')
        .then(res => {
            console.log(res);
            listBox.innerHTML = `<h1>${res.data.body} </h1>`
        })
}