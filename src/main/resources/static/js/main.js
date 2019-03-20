function getDayEntryExtended(toHide) {
    let contentToExtended = document.getElementById('dayEntry1ContentHidden');
    contentToExtended.className = 'day__row-content day__row-content--extended';
    toHide.className = 'day__entry-background day__row-content day__row-content--hidden';
}