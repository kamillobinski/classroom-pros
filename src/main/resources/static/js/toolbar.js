function showLessonEditor() {
    const ACCORDIONS = document.getElementsByClassName("accordion");
    const LESSON_PANEL = document.getElementById('accordion--lesson--panel');
    const PATH = window.location.pathname.split("-");
    let i;

    // Default accordion behavior
    for (i = 0; i < ACCORDIONS.length; i++) {
        ACCORDIONS[i].addEventListener("click", function () {
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
    if (PATH[0] === "/request" && PATH[1] === "lesson" && PATH[2] === "update") {
        ACCORDIONS[1].classList.toggle("active");
        LESSON_PANEL.style.maxHeight = LESSON_PANEL.scrollHeight + "px";

        // Select the currently edited lesson in the table
        selectEditedLesson();
    }
}

function showTitleEditor() {
    const ACCORDIONS = document.getElementsByClassName("accordion");
    const TITLE_PANEL = document.getElementById("accordion--title--panel");

    ACCORDIONS[0].classList.toggle("active");

    if (TITLE_PANEL.style.maxHeight) {
        TITLE_PANEL.style.maxHeight = null;
    } else {
        TITLE_PANEL.style.maxHeight = TITLE_PANEL.scrollHeight + "px";
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
