let listDays = new Array(32);
for( let i=1; i<32; i++){ listDays[i]=0;}

const daysTag = document.querySelector(".days"),
currentDate = document.querySelector(".current-date"),
prevNextIcon = document.querySelectorAll(".icons span");

// getting new date, current year and month
let date = new Date(),
currYear = date.getFullYear(),
currMonth = date.getMonth()-1;


// storing full name of all months in array
const months = ["January", "February", "March", "April", "May", "June", "July",
              "August", "September", "October", "November", "December"];

const renderCalendar = () => {
    let firstDayofMonth = new Date(currYear, currMonth, 1).getDay(), // getting first day of month
    lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate(), // getting last date of month
    lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay(), // getting last day of month
    lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate(); // getting last date of previous month
    let liTag = "";
	//alert(firstDayofMonth);
    //alert(currMonth);
	firstDayofMonth = firstDayofMonth -1;
	if (firstDayofMonth === -1) {let firstDayofMonth = 6;};
	lastDayofMonth = lastDayofMonth -1;
	
	if (lastDayofMonth === (-1)) {let lastDateofMonth = 6;};
	
	
	for (let i = firstDayofMonth; i > 0; i--) { // creating li of previous month last days
        liTag += `<li class="inactive">${lastDateofLastMonth - i + 1}</li>`;
    }

    for (let i = 1; i <= lastDateofMonth; i++) { // creating li of all days of current month
        // adding active class to li if the current day, month, and year matched
        let isToday = i === date.getDate() && currMonth === new Date().getMonth() 
                     && currYear === new Date().getFullYear() ? "active" : "";
        liTag += `<li class="${isToday}">${i}</li>`;
    }

    for (let i = lastDayofMonth; i < 6; i++) { // creating li of next month first days
        liTag += `<li class="inactive">${i - lastDayofMonth + 1}</li>`;
    }
    currentDate.innerText = `${months[currMonth]} ${currYear}`; // passing current mon and yr as currentDate text
    daysTag.innerHTML = liTag;
};
renderCalendar();

prevNextIcon.forEach(icon => { // getting prev and next icons
    icon.addEventListener("click", () => { // adding click event on both icons
        // if clicked icon is previous icon then decrement current month by 1 else increment it by 1
        currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;

        if(currMonth < 0 || currMonth > 11) { // if current month is less than 0 or greater than 11
            // creating a new date of current year & month and pass it as date value
            date = new Date(currYear, currMonth, new Date().getDate());
            currYear = date.getFullYear(); // updating current year with new date year
            currMonth = date.getMonth(); // updating current month with new date month
        } else {
            date = new Date(); // pass the current date as date value
        }
        renderCalendar(); // calling renderCalendar function
	for( let i=1; i<32; i++){ listDays[i]=0;}	
		
		function getEventTarget(e) {
			e = e || window.event;
			return e.target || e.srcElement; 
		}

		var ul = document.getElementById('days');
		ul.onclick = function(event) {
			var target = getEventTarget(event);
			//alert(target.innerHTML);
		
		};

		var $li = $('#days li').click(function() {
			//$li.removeClass('selected');
			if ($(this).hasClass('selected')) {
                            $(this).removeClass('selected');
                            listDays[$(this).text()]=0;
                            console.log(listDays);
                        }
                        else if (!$(this).hasClass('inactive')){
                            $(this).addClass('selected');
                            listDays[$(this).text()]=1;
                            console.log(listDays);
                        };
		});

    });
});


 function getEventTarget(e) {
        e = e || window.event;
        return e.target || e.srcElement; 
    }

    var ul = document.getElementById('days');
    ul.onclick = function(event) {
        var target = getEventTarget(event);
		//alert(target.innerHTML);
		
    };

var $li = $('#days li').click(function() {
    //$li.removeClass('selected');
    if ($(this).hasClass('selected')) {
        $(this).removeClass('selected');
        listDays[$(this).text()]=0;
        console.log(listDays);
    }
		else if (!$(this).hasClass('inactive')){
                    $(this).addClass('selected');
                    listDays[$(this).text()]=1;
                    console.log(listDays);
                };
});


//________________________________________________________________________________

src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.js">
    
		$(document).ready(function(){
			
			$.ajax({
				method: 'GET',
				url: 'http://localhost:8080/EList',
                                contentType: 'application/json',
				success: function(result) {
					console.log(result);
                                        
                                        let elem = document.getElementById('names');
                                        for (let i = 0; i < result.length; i++) {
                                            elem.innerHTML = elem.innerHTML + '<option id="' + result[i]['id'] + '">' + 
                                                    result[i]['name'] +" " +result[i]['surname'] + '</option>';    
                                        }
                                        
                                        
					//var inputvalue = result.central_bank_rates[0].rate_pct;
					//document.getElementById("tauxInteret").value = inputvalue;
					//document.getElementById("tauxInteret").value = (1 + inputvalue);
				},
				error: function ajaxError(jqXHR) {
					console.error('Error: ', jqXHR.responseText);
				}
			});
		});
                
                
//_______________________________________________________________________________________________________________


function getName(){
    
    var vacationList = "";
    for( let i=1; i<32; i++){
        if (listDays[i]===1){
            vacationList+=i+",";
        }
    }
    
    var url = 'http://localhost:8080/file?';
    url = url.concat("id=",parseInt(document.querySelector('#names option:checked').id));
    url = url.concat("&month=", currMonth+1);
    url = url.concat("&year=", currYear);
    url = url.concat("&vacationList=", vacationList);
    
    $.ajax({
        method: 'GET',
        url: url,
        contentType: 'application/json',
        dataType: 'json',
        
        success: function(response) {
            console.log(response['data']);
            
            const byteCharacters = atob(response['data']);
            const byteNumbers = new Array(byteCharacters.length);
            for (let i = 0; i < byteCharacters.length; i++) {
                byteNumbers[i] = byteCharacters.charCodeAt(i);
            }
            const byteArray = new Uint8Array(byteNumbers);
            var blob = new Blob([byteArray], { type: 'application/pdf' });
            var link = document.createElement('a');
            link.href = window.URL.createObjectURL(blob);
            link.download = "PLANTILLA REGISTRO JORNADA FILLED.pdf";
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
            link.remove();
            URL.revokeObjectURL(window.URL.createObjectURL(blob));
        },
        error: function ajaxError(jqXHR) {
            console.error('Error: ', jqXHR.responseText);
        }
   });


};
                

//let xhr = new XMLHttpRequest();
//xhr.onreadystatechange = function() {
//    if (this.readyState === 4 && this.status === 200) {
//       console.log(xhr.responseText);
//    }
//};

//xhr.open('GET', url);
//xhr.send();
    
    
