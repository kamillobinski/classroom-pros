function generatePDF() {
    const name = document.getElementById("plan-name");
    html2canvas($("#pdf-content"), {
        onrendered: function(canvas) {
            var pdf = new jsPDF('landscape');
            var imgData = canvas.toDataURL('image/png');
            pdf.addImage(imgData, 'PDF', 20, 40, canvas.width*0.2, canvas.height*0.2);
            pdf.save(name.textContent + ".pdf");
        }
    });
}