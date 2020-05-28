function showLessonEditor() {
    const accordions = document.getElementsByClassName("accordion");
    const lessonPanel = document.getElementById('accordion--lesson--panel');
    const path = window.location.pathname.split("-");
    let i;

    // Default accordion behavior
    for (i = 0; i < accordions.length; i++) {
        accordions[i].addEventListener("click", function () {
            this.classList.toggle("active");
            const panel = this.nextElementSibling;
            if (panel.style.maxHeight) {
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
            }
        });
    }

    // Opening lesson editor when path is '/request-lesson-update'
    if (path[0] === "/request" && path[1] === "lesson" && path[2] === "update") {
        accordions[1].classList.toggle("active");
        lessonPanel.style.maxHeight = lessonPanel.scrollHeight + "px";

        // Select the currently edited lesson in the table
        selectEditedLesson();
    }
}

function showTitleEditor() {
    const accordions = document.getElementsByClassName("accordion");
    const titlePanel = document.getElementById("accordion--title--panel");

    accordions[0].classList.toggle("active");

    if (titlePanel.style.maxHeight) {
        titlePanel.style.maxHeight = null;
    } else {
        titlePanel.style.maxHeight = titlePanel.scrollHeight + "px";
    }

}

function selectEditedLesson() {
    // Collect all links
    const A_TAGS = document.getElementsByTagName("a");
    // Take path of the current page
    const FULL_PATH = window.location.pathname;

    // Find the current page path in the links
    for (let i = 0; i < A_TAGS.length; i++) {
        if (A_TAGS[i].pathname == FULL_PATH) {
            // If link was found get its parent (td) and apply new style
            let parentDiv = A_TAGS[i].parentNode;
            parentDiv.style.background = "#F0F1F5";
            break;
        }
    }
}
