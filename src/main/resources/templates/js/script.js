var animation = bodymovin.loadAnimation({
    container: document.getElementById('animContainer'),
    renderer: 'svg',
    loop: true,
    autoplay: true,
    path: 'https://assets7.lottiefiles.com/packages/lf20_d0gmxgy5KG.json'
})

const player = document.querySelector("lottie-player");
player.load("https://assets3.lottiefiles.com/packages/lf20_UJNc2t.json");