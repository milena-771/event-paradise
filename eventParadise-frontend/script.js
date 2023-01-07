//Set a min attribute for input date
const newDate = new Date();
let today = newDate.toISOString().substring(0, 10);//get the date in ISO format and extract only date without time

const date = document.getElementById("date");//get the input date

date.setAttribute("min", today);//Create and set an attribute min starting today

//Select my form
let form = document.querySelector("form");

const elements = form.elements;

//Create an option object, to assign default options for our futur tooltips
const options = {
    title:"Message par défaut", //title by default otherwise tooltip cannot appear
    customClass:"custom-tooltip"
}

//Loop on elements to isolate the inputs
for (const element of elements){
    const type = element.type;
    const elementName = element.name;
    //Check if one or several inputs are invalids
    if( type != "submit"){ 
        element.addEventListener("invalid", (event) => { //Create a callback function when an input is invalid
            event.preventDefault(); //Disable default beahvior of html
            
            //add bootstrap classes to make appear an input as invalid
            element.classList.add("is-invalid");

            let helpText = document.getElementById(`${elementName}-text`);
            helpText.classList.add("text-danger");

            let message = "";

            //check if input is invalid because of value Missing and custom the message in the tooltip
            if(element.validity.valueMissing){ 
                message = "Ce champs est obligatoire";
            }else if(elementName == "date" && element.validity.rangeUnderflow){  //check if the date is under the min attribute
                message = "Doit être égale ou supérieure à aujourd'hui";
            }else if(elementName == "price" && element.validity.rangeUnderflow){  //check if the price is under 0
                message = "Doit être positif";
            }

            //Create or get a tooltip from an element with specified options
            const tooltip = bootstrap.Tooltip.getOrCreateInstance(element, options);
            
            //modified the message after submitted form if input is still invalid
            tooltip.setContent({'.tooltip-inner' : message});
            
            let firstInvalidElement = document.querySelector(".is-invalid");//get first invalid input
            if(firstInvalidElement == element){//after change if the element on focus is first invalid element => focus()
                firstInvalidElement.focus();
            }
            
        })  

        //Create a function when an input is changed by the user
        element.addEventListener("change", (event) => {
            if(element.checkValidity()){

                element.classList.remove("is-invalid");
                element.classList.add("is-valid");
                
                let helpText = document.getElementById(`${elementName}-text`);
                helpText.classList.remove("text-danger"); 
                helpText.classList.add("text-success"); 

                const tooltip = bootstrap.Tooltip.getOrCreateInstance(element);

                tooltip.dispose();//destroy the tooltip
            }

        })

    }  
       
}

//*****GET LOCATIONS LIST*****//
const selectLocation = document.querySelector("#location");//target to insert our list from the api
let contentLocations = locationByDefault();//call the function to display the first line of the list

const locations = await getLocationsList();//call the function to get the Locations list from the api

//add a new list item in an element <option>
locations.forEach(city =>{
    contentLocations += locationsList(city);//call the function to insert each property of each city 
})
selectLocation.innerHTML = contentLocations;//insert the <option> element in our select element

//function to insert each property of the object location from the api in the <option> element
function locationsList(city){
    return `<option value="${city.id}">${city.name}</option>`
}

//function to select the first line when form is loaded
function locationByDefault(){
    return `<option value="">Choisir un lieu dans la liste :</option>`
}

//function to fetch our locations list from eventParadise api
async function getLocationsList(){

    const url = "http://localhost:8080/locations";

    const options = {
        method: "GET"
    };

    const response = await fetch(url, options);
    return await response.json();
}

//*******GET THEMES LIST*****/
const selectTheme = document.querySelector("#theme");
let contentThemes = themeByDefault();

const themes = await getThemesList();

themes.forEach(theme => {
    contentThemes += themeList(theme);
})
selectTheme.innerHTML = contentThemes

function themeList(theme){
    return `<option value="${theme.id}">${theme.name}</option>`
}

function themeByDefault(){
    return `<option value="">Choisir un thème dans la liste :</option>`
}

async function getThemesList(){
    const url = "http://localhost:8080/themes";

    const options = {method:"GET"};

    const response = await fetch(url,options);
    return await response.json();
}

//*****POST FORM****//

const data = { //Create an object data to represent dataModel from the api
    name: null,
    date: null,
    locationId: null,
    themeId: null,
    price: null,
    description: null
};

const properties = Object.keys(data); //retrieve all properties from data Object

//Data-binding View to Model & Model to View with api
function bind(){
    properties.forEach((property) =>{
        const element = form.elements[property];
        element.value = data[property];
        element.addEventListener("change",(event) => {
            data[property] = element.value;
        })
    })
}

bind();

//Function to post the form to the api
async function postForm(){
    const options = {
        method: "POST",
        headers:{
            "Content-Type": "application/json"
        },
        body:JSON.stringify(data)
    };
    const url = "http://localhost:8080/events";
    let response = await fetch(url, options)
    if(response.ok){//Check if there's any error in the form from the server    
        return "no error";//if it is true, return a string   
    }else{
        const headers = response.headers;
        if(headers.get("content-type") == "application/json"){
        return await response.json(); //otherwise, return a response in JSON object
        }
        
    }
}

form.addEventListener("submit", async (event) => {
        event.preventDefault();
        const response =  await postForm();
        if(response == "no error"){
            form.reset();
            displayToaster();//the function for the toaster is called     
            for(const element of elements){
                const type = element.type;
                const elementName = element.name;
                if(type != "submit"){
                    element.classList.remove("is-valid");
                    let helpText = document.querySelector(`#${elementName}-text`);
                    helpText.classList.remove("text-success"); 
                }                  
            }
        }else{
            form.reportValidity();
        }
                               
})

//Function to display a toaster
function displayToaster(){
    const myToast = document.querySelector('.toast');
    const toast = bootstrap.Toast.getOrCreateInstance(myToast);
    toast.show();
}



        





















