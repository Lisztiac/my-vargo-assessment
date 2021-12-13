$(window).on("DOMContentLoaded", () => {

    const input = $("#searchInput");
    const searchButton = $("#searchButton");
    const searchError = $("#searchError");
    const errorDisplay = $("#errorDisplay")

    $("#searchForm").on("submit", (e) => search(e));
    $("#clearButton").on("click", clearSearch);
    input.on("input", validateQuery);
    clearSearch();

    function search(e) {
        e.preventDefault();
        const query = input.val().trim();
        if (!isValid(query)) return;

        disableSearch();

        const handleSuccess = (data) => {
            $("#tableArea").html(data);
            validateQuery();
        }
        const handleError = () => validateQuery();

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/employee/projects",
            data: JSON.stringify({ id: query }),
            success: handleSuccess,
            error: handleError,
        });
    }

    function clearSearch() {
        input.val("");
        validateQuery();
        input.focus();
    }

    function validateQuery() {
        let isValidSearch = isValid(input.val().trim());

        if (!isValidSearch) {
            disableSearch();
            displayError(errorDisplay, "Id must be greater than zero");
        }
        else {
            searchButton.removeClass("grayout");
            searchButton.removeAttr("disabled");
        }
    }

    function isValid(query) {
        return query.length > 0
            && !query.includes(".")
            && !query.includes("-")
            && !Number.isNaN(Number.parseFloat(query))
            && !isNaN(query);
    }

    function disableSearch() {
        if (!searchButton.hasClass("grayout")) {
            searchButton.addClass("grayout");
            searchButton.attr("disabled", "disable");
        }
    }

    function displayError() {
        if (input.val().trim().length !== 0) {
            searchError.css("visibility", "visible");
        }
        else {
            searchError.css("visibility", "hidden");
        }
    }
});