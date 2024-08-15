const mostraDataRodape = () => {
    const dataRodape = document.getElementById("dataRodape");
    const data = new Date();
    dataRodape.innerHTML = "Data: " + data.toLocaleString();
    setTimeout(mostraDataRodape, 1);
}
window.onload = () => {
    mostraDataRodape();
};

const login = (dados) => {
    const request = new Request("https://example.com", {
        method: "POST",
        body: dados,
    });

    fetch(request)
        .then((response) => {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error("Something went wrong on API server!");
            }
        })
        .then((response) => {
            console.debug(response);
            // …
        })
        .catch((error) => {
            console.error(error);
        });
};
   

