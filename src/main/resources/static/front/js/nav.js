// 获取需要隐藏和显示的元素
const registerLink = document.querySelector('a[href="register2.html"]');
const loginLink = document.querySelector('a[href="login2.html"]');
// const editLink = document.querySelector('a[href="edit.html"]');
const chatLink = document.querySelector('a[href="chat.html"]');
const logoutLink = document.querySelector('a[href="#logout"]');
const email = sessionStorage.getItem('email');
const mname = sessionStorage.getItem('mname');
const mpic = sessionStorage.getItem('mpic');
const membno = sessionStorage.getItem('membno');
const pageHeader = document.getElementById('Page Header Start');
const factitem = document.getElementById('factitem');
const image1 = document.getElementById('avatar');

// const manageLink = document.querySelector('a[href="manage.html"]');

// 登录成功后执行的操作
function loginSuccess() {
    // 隐藏注册和登录链接
    registerLink.style.display = 'none';
    loginLink.style.display = 'none';

    // 显示编辑、聊天、管理和登出链接
    // editLink.style.display = 'inline';
    chatLink.style.display = 'inline';
    logoutLink.style.display = 'inline';
    pageHeader.style.display = 'inline';
    factitem.style.display = 'inline';
    image1.style.display = 'inline';
    // manageLink.style.display = 'inline';
}

if (email) {
    loginSuccess();
    document.querySelector('#currentUser').textContent = mname;


    const imageBinartStr = atob(mpic);
    let len = imageBinartStr.length;
    const uint8Array = new Uint8Array(len);

    for (let i = 0; i < len; i++) {
        uint8Array[i] = imageBinartStr.charCodeAt(i);
    }

    const blob = new Blob([uint8Array]);
    document.querySelector('#avatar').src = URL.createObjectURL(blob);


    // fetch(`/pictureReader?membno=${membno}`)
    //     .then(response => response.blob())
    //     .then(blob => {
    //         const objectURL = URL.createObjectURL(blob);
    //         document.querySelector('#avatar').src = objectURL;
    // document.querySelector('#avatar').src = `data:image/jpeg;base64,${mpic.mpic}`;
    //     })
} else {

    registerLink.style.display = 'inline';
    loginLink.style.display = 'inline';

    logoutLink.style.display = 'none';
    chatLink.style.display = 'none';
    // editLink.style.display = 'none';
    pageHeader.style.display = 'none';
    factitem.style.display = 'none';
    image1.style.display = 'none';

}

logoutLink.addEventListener('click', () => {
    // sessionStorage.removeItem('email');
    // sessionStorage.removeItem('mpic');
    sessionStorage.clear();

    location = `../front/index_Chian.html`;
});
