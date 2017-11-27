$(function () {
    init();
});

function init() {
    $("#category").change(function () {
        var term = $(this).val();

        getSubCatList(term);
        
        
    });
    
    //$("#subCategory option").text('{{categoryName}}');

    getSubCatList($("#category").val());
    
}

function doSearch(term) {
    console.log("Performing search for " + term);
    $.get("/api/search", {"term": term}, showList);
}

/*function fillList(list) {
    console.log("displaying " + list);
    
    alert(JSON.stringify(list));
   //alert(list);
    /*if (list.length === 0) {
        console.log("hiding list as there no data");
        w3.hide("#book-list");
        w3.show("#no-result")
    } else {
        w3.hide("#no-result")
        w3.show("#book-list");
        w3.displayObject("book-list", {"books": list});
    }
}*/

function getSubCatList(id){
    console.log("Performing search for " + id);
    $.get("/api/subCategory", {"id": id}, fillList);
   
    //w3.getHttpObject("/api/subCategory?id",{"id": id}, myFunction);
    
}


function fillList(myObject) {
    console.log("displaying JSON " + JSON.stringify({"subCategories":myObject}));
  w3.displayObject("subCategory", {"subCategories":myObject});
}