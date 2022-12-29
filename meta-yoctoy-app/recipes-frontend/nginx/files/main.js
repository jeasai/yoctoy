const works = "✅";
const fails = "❌";

const CStatusId = "CStatus";
const RustStatusId = "RustStatus";
const KModStatusId = "KModStatus";
const ApiUrl = "http://raspberry.pi:80/api/v1/";

function SetTextOfDiv(divId, map, id) {
    var item = document.getElementById(divId);

    if (map == null) {
        item.innerHTML = fails;
        return;
    }

    if (map.get(id)) {
        item.innerHTML = works;
    } else {
        item.innerHTML = fails;
    }
}

function loadFunction() {
    // Load status of components
    const xhr = new XMLHttpRequest();

    xhr.open("GET", ApiUrl);

    xhr.onload = function() {
        if (xhr.status === 200) {
            data = new Map(Object.entries(JSON.parse(xhr.responseText)));
            console.log(data);

            SetTextOfDiv(CStatusId, data, "c binary");
            SetTextOfDiv(RustStatusId, data, "rust binary");
            SetTextOfDiv(KModStatusId, data, "kernel module");
        } else {
            SetTextOfDiv(CStatusId, null, null);
            SetTextOfDiv(RustStatusId, null, null);
            SetTextOfDiv(KModStatusId, null, null);
        }
    };

    xhr.send();
}

// Function called after the page is fully loaded
window.onload = loadFunction();
