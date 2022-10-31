// const axios = require('axios').default;
const BASE_URL = "http://localhost:8081"


let user = {
    id: 1,
    nickName: "bhb",
    email: "@mail.ru",
    password: "ddd",
    auth: false
}

let post = {
    pubId: 1,
    imgLink: "images/ph1.jpg",
    userDto: user, //т.к.JS сам определяет тип данных решил передать сам объект.
    description: "some description",
    dateTimeOfPublication: new Date(),
    isLiked: false
}


let comment = {
    id: 1,
    publication: post,
    commText: "hello",
    whoComm: user,
    date: new Date()
}

let posts = [];


function userS(user1) {
    if (user1.auth === false) {
        user1.auth = true;
        return user1;
    }
    user1.auth = false;
    return user1;
}

function addToPosts(somePost) {
    posts.push(somePost);
}

function likeAndDis(postId) {
    for (let i = 0; i < posts.length; i++) {
        if (posts[i].id === postId) {
            if (posts[i].isLiked === false) {
                posts[i].isLiked = true;
            } else {
                posts[i].isLiked = false;
            }

        }
    }
}

//Home worke 58
let hideBtn = document.getElementById('hide');
hideBtn.addEventListener("click", function () {
    hideSplashScreen();
    alert("hide")
})

let showBtn = document.getElementById('show');
showBtn.addEventListener("click", function () {
    showSplashScreen();
    alert("show");
});

//Task-1
function hideSplashScreen() {
    document.getElementsByClassName("splash")[0].hidden = true
}

function showSplashScreen() {
    document.getElementsByClassName("splash")[0].hidden = false;
}


//Task-2

function createCommentElement(comment, pubId) {
    let newComm = document.createElement('div');
    const htmlComm = `
    <div class="alert alert-primary" role="alert">
        <p>commText: ${comment.commText}</p>
        <p>commDate: ${comment.dateTimeOfDescription}</p>
        <p>commUserEmail: ${comment.userDto.email}</p>
        <p>commUserName: ${comment.userDto.nickName}</p>
    </div>`
    newComm.innerHTML = htmlComm;
    document.getElementById("comm-box"+pubId).prepend(newComm);
}

//Task-3

function createPostElement(post) {
    let newPost = document.createElement('div');
    const htmlPost = `
        <div class="post-box container">
            <div class="img-box">
            <img ondblclick="likeHeartEvent(${post.pubId})" id="img${post.pubId}" class="post-img" src="${post.imgLink}" alt="Post image" >
                <span id="heart-on-img${post.pubId}" class="h1 img-heart">
                <i class = "bi bi-heart-fill" ></i>
                </span>
            </div>
            <div class="d-inline-flex">
                <div>
                    <span onclick="likeEvent(${post.pubId})" id="heart${post.pubId}" class = "h1 mx-2 heart">
                        <i class = "bi bi-heart-fill" > </i>
                    </span>
                </div>
                <div class="text-center">
                    <span onclick="addInBookmarks(${post.pubId})" id = "bookmark${post.pubId}" class = "h1 mx-2 bookmark">
                        <i class = "bi bi-bookmark-fill "></i>
                    </span>
                </div>
                <div>
                    <span onclick="showHideComments(${post.pubId})" id="comm${post.pubId}" class = "h1 mx-2">
                        <i class="bi bi-chat-left-dots"></i>
                    </span>
                </div>
            </div>    
            <p>description: ${post.description}</p>
            <p>date: ${post.dateTimeOfPublication}</p>
            <p>userEmail: ${post.userDto.email}</p>
            <p>userName: ${post.userDto.nickName}</p>
            
            <div class="modal fade" id="commentModal${post.pubId}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="display: none">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Post comments</h5>
                    </div>
                    <div class="modal-body">
                        <div id="comm-box${post.pubId}">
                            
                        </div>
                        <form class="comment-form">
                            <div class="mb-3">
                                <label class="form-label">You can add comment</label>
                                <textarea required type="text" class="form-control" name="commText"></textarea>
                            </div>
                            <input type="hidden" name="userId" value="1">
                            <input type="hidden" name="pubId" value="${post.pubId}">
                            <button type="submit" class="btn btn-primary" id="add-comm-btn">Add comment</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button onclick="showHideComments(${post.pubId})" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        </div>`
    newPost.innerHTML = htmlPost;
    return newPost;
}


// Task-4

function addPost(postElement) {

    let addCommBtn = postElement.getElementsByClassName("comment-form")[0];
    addCommBtn.addEventListener("submit", addComment);
    function addComment(event){
        event.preventDefault();
        const form = event.target;
        const formData = new FormData(form);

        let object = {};

        formData.forEach((value, key) => {
            object[key] = value;
        });
        const pubId = object['pubId'];
        sendCommAdd(formData, pubId);
    }
    document.getElementsByClassName("splash-box")[0].after(postElement);
}


//HomeWork 59

//Task-1
function likeEvent(postId) {
    let heart = document.getElementById("heart" + postId);
    if (heart.style.color == "grey") {
        heart.style.color = 'red';
    } else {
        heart.style.color = 'grey';
    }
}


//Task-2
function likeHeartEvent(postId) {
    let heart = document.getElementById("heart" + postId);
    heart.style.color = "red";
    let likeOnImg = document.getElementById("heart-on-img" + postId);
    likeOnImg.style.display = "block";
    setTimeout(function () {
        likeOnImg.style.display = "none"
    }, 2000);
}

//Task-3

function addInBookmarks(postId) {
    let bookmark = document.getElementById("bookmark" + postId);

    if (bookmark.style.color == "grey") {
        bookmark.style.color = 'black';
    } else {
        bookmark.style.color = 'grey';
    }
}

//Task-4

let enterBtn = document.getElementById("btn");
enterBtn.addEventListener("click", hideSplash);

function hideSplash() {
    document.getElementsByClassName("splash-auth")[0].hidden = true;
}


//HomeWork-60

//Task-1

let postAdd = document.getElementById("post-add-form");
postAdd.addEventListener("submit", add);

function add(event) {
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    let object = {};

    formData.forEach((value, key) => {
        object[key] = value;
    });

    const image = object['image'];
    const desc = object['description'];
    const userId = object['user-id'];
    sendPost(image, desc, userId)
}


function sendPost(image, description, userId) {
    axios.post(BASE_URL + '/publications/add', {image, description, userId},
        {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        })
        .then((response) => {
            console.log(response.data.body);
            addPost(createPostElement(response.data.body));
        })
        .catch(function (error){
        console.log(error);
        })
}

//Task-2
function showHideComments(postId) {
    let comm = document.getElementById("commentModal" + postId);
    console.log(comm)
    if (comm.style.display == "none") {
        sendGetComms(postId)
        comm.style.display = 'block';
        comm.style.opacity = '1';
        console.log(comm)
    } else {
        commBoxCleaner(postId);
        comm.style.display = 'none';
        comm.style.opacity = '0';
        console.log(comm)
    }

}

function sendCommAdd(formData, pubId) {
    axios.post(BASE_URL + '/comment/add', formData)
        .then((response) => {
            const comm = response.data.body;
            console.log(comm);
            createCommentElement(comm, pubId);
        })
        .catch(function (error){
            console.log(error);
        })
}
function sendGetComms(pubId) {
    axios.get(BASE_URL + '/comment/comments/' + pubId)
        .then((response) => {
            const comms = response.data;
            console.log(comms);
            comms.forEach( e => {createCommentElement(e, pubId)});
        })
        .catch(function (error){
            console.log(error);
        })
}
function commBoxCleaner(pubId){
    document.getElementById("comm-box"+pubId).innerHTML = "";
}

//HomeWork-61

function sendForGetAllPubs(){
    axios.get(BASE_URL + '/publications/all')
        .then((response) => {
        const publications = response.data;
        publications.forEach(e => {addPost(createPostElement(e))})

    })
        .catch(function (error){
            console.log(error);
        })
}
    sendForGetAllPubs();

//HomeWork-62

let reg = document.getElementById("registerModal");
function hideShowRegModal(){
    if (reg.style.display == "none") {
        reg.style.display = 'block';
        reg.style.opacity = '1';
    } else {
        reg.style.display = 'none';
        reg.style.opacity = '0';
    }
}


const regForm = document.getElementById("reg-form");
regForm.addEventListener("submit", createRegRequest)

function createRegRequest(event){
    event.preventDefault();
    const form = event.target;
    const formData = new FormData(form);

    let object = {};

    formData.forEach((value, key) => {
        object[key] = value;
    });
    console.log(object);
    sendRegData(formData);
}

function sendRegData(formData){
    axios.post(BASE_URL + '/user/register', formData)
        .then((response) => {
            const regAnswer = response.data;
            console.log(regAnswer);
            regAnswerEvent(regAnswer);
        })
        .catch(function (error){
            console.log(error);
        })
}

function regAnswerEvent(regAnswer){
    const regMsgTrue = document.getElementById("reg-text-true");
    const regMsgFalse = document.getElementById("reg-text-false");
    if(regAnswer === true){
        regMsgTrue.style.display = "block";
        setTimeout(function () {
            regMsgTrue.style.display = "none"
        }, 2000);
    }else {
        regMsgFalse.style.display = "block";
        setTimeout(function () {
            regMsgFalse.style.display = "none"
        }, 2000);
    }

}

