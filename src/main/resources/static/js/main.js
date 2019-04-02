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

function newDayDish() {
    // console.log(date);

    let request = new XMLHttpRequest();
    request.onreadystatechange = () => {
        if(request.readyState === XMLHttpRequest.DONE) {
            let newDish = document.createElement('div');
            newDish.innerHTML = request.responseText;
            document.getElementById('dayList').appendChild(newDish);
        }
    }
    request.open('GET', 'http://localhost:8080/newDayDish?date='); // TODO change link to non static
    request.send();
}

function newIngredient(id) {
    let request = new XMLHttpRequest();
    request.onreadystatechange = () => {
        if(request.readyState === XMLHttpRequest.DONE) {
            let newIngredient = document.createElement('div');
            newIngredient.innerHTML = request.responseText;
            console.log('Want: ' + 'ingredientList-' + id);
            document.getElementById('ingredientList-' + id).appendChild(newIngredient);
        }
    }
    request.open('GET', 'http://localhost:8080/newIngredient?dishId=' + id); // TODO change link
    request.send();
}

function saveDayDish(id) {
    console.log("saveDayDishId: " + id);
    let request = new XMLHttpRequest();
    request.onreadystatechange = () => {
        if(request.readyState === XMLHttpRequest.DONE) {
            if(request.status !== 200) {
                console.log('Something went wrong. Dish is not saved! Code: ' + request.responseText);
            }
        }
    }

    // TODO sometime i don't get js
    let ingredientList = document.getElementById('ingredientList-' + id).getElementsByTagName('li'); // DIV (around li)
    // console.log('ingredientList: ' + ingredientList);
    // console.log('ingredientList[0]: ' + ingredientList[0]);
    // console.log('ingredients[0 ' + ingredients.children.());

    let ingredientsToSave = [];

    for(let i = 0; i < ingredientList.length; i++) {
        // console.log(ingredientList.item(i));
        // console.log("0 " + ingredientList.item(i).children[0].getAttributeNode('value').value);
        // console.log("1 " + ingredientList.item(i).children[1].getAttributeNode('value').value);
        // console.log("2 " + ingredientList.item(i).children[2].getAttributeNode('value').value);
        let ingredientToSave = {
            quantity: ingredientList.item(i).children[0].value,
            unit: {
                name: ingredientList.item(i).children[1].value,
            },
            name: ingredientList.item(i).children[2].value,
        };
        ingredientsToSave.push(ingredientToSave);
    }

    let dayEntryDish = {
        id: id,
        localTime: document.getElementById('dish-time-' + id).value,
        dishName: document.getElementById('dish-name-' + id).value,
        dishIngredientDtos: ingredientsToSave,
        commentText: document.getElementById('dish-cmt-' + id).innerText,
        quantity: document.getElementById('dish-quantity-' + id).value,
        unit: {
            name: document.getElementById('dish-unit-' + id).value
        }
    };

    request.open('POST', 'http://localhost:8080/saveDishRow'); // TODO change link
    request.setRequestHeader("Content-Type", "application/json");

    console.log('trying to send dish: ' + JSON.stringify(dayEntryDish));
    request.send(JSON.stringify(dayEntryDish));
}

// Open the registration popup if there was an error
// let url = window.location.href.split("/")[window.location.href.split("/").length - 1];
// console.log(url);
// if(url === "registration") {
//     location.href = "#pop-reg"; // TODO dont let it be in the browser history
// }