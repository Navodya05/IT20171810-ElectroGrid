$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 

	// Form validation-------------------
	var status = validateEmployeeForm();
	if (status != true) 
	 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 return; 
 } 
// If valid------------------------
var type = ($("#hididSave").val() == "") ? "POST" : "PUT"; 
 	$.ajax( 
 	{ 
 		url : "EmployeeServlet", 
 		type : type, 
 		data : $("#formEmployee").serialize(), 
 		dataType : "text", 
 		complete : function(response, status) 
 		{ 
 			onEmployeeSaveComplete(response.responseText, status); 
 		} 
 	}); 
});

function onEmployeeSaveComplete(response, status)
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
	 	 { 
		 	$("#alertSuccess").text("Successfully saved."); 
		 	$("#alertSuccess").show(); 
		 	$("#divEmployeeGrid").html(resultSet.data); 
		 } 
		 else if (resultSet.status.trim() == "error") 
		 { 
		 	$("#alertError").text(resultSet.data); 
		 	$("#alertError").show(); 
		 } 
		 } 
		 else if (status == "error") 
		 { 
		 	$("#alertError").text("Error while saving."); 
		 	$("#alertError").show(); 
		 } 
		 else
		 { 
		 	$("#alertError").text("Unknown error while saving.."); 
		 	$("#alertError").show(); 
	 }
	$("#hididSave").val(""); 
	$("#formEmployee")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{ 
	 $("#hididSave").val($(this).data("id")); 
	 $("#empName").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#empAddress").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#empEmail").val($(this).closest("tr").find('td:eq(2)').text());  
	 $("#empContactno").val($(this).closest("tr").find('td:eq(3)').text()); 
});

$(document).on("click", ".btnRemove", function(event)
{ 
	 $.ajax( 
	 { 
		 url : "EmployeeServlet", 
		 type : "DELETE", 
		 data : "id=" + $(this).data("id"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
			 onEmployeeDeleteComplete(response.responseText, status); 
		 } 
	}); 
});
		
function onEmployeeDeleteComplete(response, status)
{ 
	if (status == "success") 
	 { 
		 var resultSet = JSON.parse(response); 
		 if (resultSet.status.trim() == "success") 
		 { 
			 $("#alertSuccess").text("Successfully deleted."); 
			 $("#alertSuccess").show(); 
			 $("#divEmployeeGrid").html(resultSet.data); 
		 }
		 else if (resultSet.status.trim() == "error") 
		 { 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
		 } 
		 }
		 else if (status == "error") 
		 { 
			 $("#alertError").text("Error while deleting."); 
			 $("#alertError").show(); 
		 } 
		 else
		 { 
		 $("#alertError").text("Unknown error while deleting.."); 
		 $("#alertError").show(); 
	 } 
}


// CLIENT-MODEL================================================================
function validateEmployeeForm()
{
	// NAME
	if ($("#empName").val().trim() == ""){
	return "Insert EMPName.";
	}
	// ADDRESS
	if ($("#empAddress").val().trim() == ""){
	return "Insert EMPAddress.";
	}
	// EMAIL
	if ($("#empEmail").val().trim() == ""){
	return "Insert EMPEmail.";
	}
	// CONTACT------------------------
	if ($("#empContactno").val().trim() == ""){
	return "Insert EMPContactNO.";
	}	
	return true;
}