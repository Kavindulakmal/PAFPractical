$(document).ready(function () {
    if ($("#alertSuccess").text().trim() == "") {
        $("#alertSuccess").hide();
    }
    $("#alertError").hide();
});


// CLIENT-MODEL================================================================
function validateItemForm() {
    // firstName
    if ($("#first_name").val().trim() == "") {
        return "Insert first Name.";
    }
    // lastName
    if ($("#last_name").val().trim() == "") {
        return "Insert last Name.";
    }
    // Email
    if ($("#email").val().trim() == "") {
        return "Insert user Email.";
    }
    // address-------------------------------
    if ($("#address").val().trim() == "") {
        return "Insert address.";
    }
    // username-------------------------------
    if ($("#username").val().trim() == "") {
        return "Insert username.";
    }


    // password------------------------
    if ($("#password").val().trim() == "") {
        return "Insert password";
    }
    return true;
}

$(document).on("click", "#btnSave", function (event) {
    // Clear alerts---------------------
    $("#alertSuccess").text("");
    $("#alertSuccess").hide();
    $("#alertError").text("");
    $("#alertError").hide();
    // Form validation-------------------
    var status = validateItemForm();
    if (status != true) {
        $("#alertError").text(status);
        $("#alertError").show();
        return;
    }
    // If valid------------------------
    var type = ($("#hiduserIDSave").val() == "") ? "POST" : "PUT";
    $.ajax(
        {
            url: "UserAPI",
            type: type,
            data: $("#UserItem").serialize(),
            dataType: "text",
            complete: function (response, status) {
                onItemSaveComplete(response.responseText, status);
            }
        });
});

function onItemSaveComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success") {
            $("#alertSuccess").text("Successfully saved.");
            $("#alertSuccess").show();
            $("#divItemsGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Error while saving.");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unknown error while saving..");
        $("#alertError").show();
    }
    $("#hidItemIDSave").val("");
    $("#UserItem")[0].reset();
}

$(document).on("click", ".btnUpdate", function (event) {
    $("#hiduserIDSave").val($(this).data("itemid"));
    $("#first_name").val($(this).closest("tr").find('td:eq(0)').text());
    $("#last_name").val($(this).closest("tr").find('td:eq(0)').text());
    $("#email").val($(this).closest("tr").find('td:eq(1)').text());
    $("#address").val($(this).closest("tr").find('td:eq(2)').text());
    $("#username").val($(this).closest("tr").find('td:eq(3)').text());
    $("#password").val($(this).closest("tr").find('td:eq(4)').text());
});

$(document).on("click", ".btnRemove", function (event) {
    $.ajax(
        {
            url: "UserAPI",
            type: "DELETE",
            data: "userID=" + $(this).data("userid"),
            dataType: "text",
            complete: function (response, status) {
                onItemDeleteComplete(response.responseText, status);
            }
        });
});

function onItemDeleteComplete(response, status) {
    if (status == "success") {
        var resultSet = JSON.parse(response);
        if (resultSet.status.trim() == "success") {
            $("#alertSuccess").text("Successfully deleted.");
            $("#alertSuccess").show();
            $("#divResearchGrid").html(resultSet.data);
        } else if (resultSet.status.trim() == "error") {
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") {
        $("#alertError").text("Error while deleting.");
        $("#alertError").show();
    } else {
        $("#alertError").text("Unknown error while deleting..");
        $("#alertError").show();
    }
}