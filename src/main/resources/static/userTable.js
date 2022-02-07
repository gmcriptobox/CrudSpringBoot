fetch("http://localhost:8082/api/about").then(
    res=>{
            res.json().then(
                data=> {
                    let temp = `<tr>
                         <td>${data.id}</td>
                         <td>${data.name}</td>  
                         <td>${data.secondName}</td>  
                         <td>${data.age}</td>  
                         <td>${data.mail}</td>  
                         <td>${data.rolesString}</td></tr>`
                        document.getElementById("tableBody").innerHTML = temp;
                }
            )
    }
)
