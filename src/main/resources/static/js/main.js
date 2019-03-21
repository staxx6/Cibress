function extendDishContent(toHide) {
    let id = toHide.id.split("-")[1];
    let contentToExtended = document.getElementById('dishContentExtended-' + id);
    // console.log('want: ' + 'dishContentExtended-' + id + ' got: ' + contentToExtended);
    contentToExtended.className = 'day__row-content day__row-content--extended';
    toHide.className = 'day__entry-background day__row-content day__row-content--hidden';
}

// TODO
function getDayEntryShrink(toHide) {
    let contentToExtended = document.getElementById('dayEntry1ContentHidden');
    contentToExtended.className = 'day__row-content day__row-content--extended';
    toHide.className = 'day__entry-background day__row-content day__row-content--hidden';
}

// Open the registration popup if there was an error
// let url = window.location.href.split("/")[window.location.href.split("/").length - 1];
// console.log(url);
// if(url === "registration") {
//     location.href = "#pop-reg"; // TODO dont let it be in the browser history
// }