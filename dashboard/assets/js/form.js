// ✅ Add Content Block
function addContentBlock() {
  const container = document.getElementById("contentBlockWrapper");
  const block = document.createElement("div");
  block.className = "content-block";
  block.innerHTML = `
    <label>Title </label>
    <input type="text" class="block-title" maxlength="100" required>

    <label>Content</label>
    <textarea class="block-details" maxlength="350" required></textarea>
  `;
  container.appendChild(block);
}

// ✅ Add FAQ Field
function addFaqField() {
  const container = document.getElementById("faqFieldGroup");
  const field = document.createElement("div");
  field.className = "faq-field";
  field.innerHTML = `
    <label>Heading</label>
    <input type="text" class="faq-title" maxlength="100" required>

    <label>Content</label>
    <textarea class="faq-text" maxlength="350" required></textarea>
  `;
  container.appendChild(field);
}


// ✅ Add Question Block
function addQuestionBlock() {
  const container = document.getElementById("questionContainer");
  const block = document.createElement("div");
  block.className = "question-item";
  block.innerHTML = `
    <label>Title</label>
    <input type="text" class="question-input" maxlength="100" required>

    <label>Content</label>
    <textarea class="answer-input" maxlength="350" required></textarea>
  `;
  container.appendChild(block);
}

// ✅ Form Validation (applies to all content forms)
function validateInterviewForm(event) {
  const form = event.target;
  const formId = form.id;

  // Skip validation for Blog and Case Study if they exist
  if (formId === "BlogForm" || formId === "CaseStudyForm") return true;

  const inputs = form.querySelectorAll("input[type='text'], textarea");
  const allowedPattern = /^[A-Za-z0-9@#&()\[\]{}.,'"\-!?\/:;_+%=\s]*$/;

  for (let input of inputs) {
    if (!input.value.trim()) {
      alert("⚠️ Please fill out all fields before submitting.");
      input.focus();
      return false;
    }

    const maxLength = input.tagName === "TEXTAREA" ? 350 : 100;
    if (input.value.length > maxLength) {
      alert(
        `⚠️ "${
          input.previousElementSibling?.innerText || "Field"
        }" exceeds ${maxLength} characters.`
      );
      input.focus();
      return false;
    }

    if (!allowedPattern.test(input.value)) {
      alert(
        `⚠️ Invalid characters found in "${
          input.previousElementSibling?.innerText || "Field"
        }".`
      );
      input.focus();
      return false;
    }
  }

  return true;
}

// ✅ Attach validation to all forms
document.addEventListener("DOMContentLoaded", () => {
  document.querySelectorAll("form").forEach((form) => {
    form.addEventListener("submit", validateInterviewForm);
  });
});

// ✅ Toggle user dropdown
function toggleDropdown() {
  const dropdown = document.getElementById("userDropdown");
  dropdown.style.display = dropdown.style.display === "flex" ? "none" : "flex";
}

// ✅ Filter + Content Type Data
const filterData = {
  topic: [
    "AI & Data Analytics",
    "Cloud Services",
    "Cyber Security",
    "Web & Mobile Apps",
    "Marketing & Experience",
    "Tech Consulting",
    "VFX & Game Design",
  ],
  industry: [
    "Aerospace & Defense",
    "Automotive",
    "Banking",
    "Chemicals",
    "Communications & Media",
    "Consumer Goods & Services",
    "Health",
    "Industrial",
    "Insurance",
    "Life Sciences",
    "Natural Resources",
    "Private Equity",
    "Public Service",
    "Retail",
    "Software & Platforms",
    "Travel",
    "Utilities",
  ],
  content: [
    "Blog",
    
    "Live Interview",
    "News Article",
    "Perspective",
    "Podcast",
    "Research Repo",
  ],
};

// ✅ Map form elements
const contentForms = {
  Blog: document.getElementById("BlogForm"),
  "Case Study": document.getElementById("CaseStudyForm"),
  "Live Interview": document.getElementById("LiveInterviewForm"),
  "News Article": document.getElementById("NewsArticleForm"),
  Perspective: document.getElementById("PerspectiveForm"),
  Podcast: document.getElementById("PodcastForm"),
  "Research Repo": document.getElementById("ResearchForm"),
};

// Hide all forms initially
Object.values(contentForms).forEach((f) => f && (f.style.display = "none"));

const filterItems = document.querySelectorAll(".filter-item");
const expandedRow = document.getElementById("expandedRow");

// ✅ Filter button interactions
filterItems.forEach((item) => {
  item.addEventListener("click", () => {
    const type = item.dataset.content;
    const isOpen =
      expandedRow.style.display === "grid" && expandedRow.dataset.type === type;

    if (isOpen) {
      expandedRow.style.display = "none";
      item.querySelector(".toggle").textContent = "+";
      expandedRow.dataset.type = "";
      return;
    }

    filterItems.forEach((i) => (i.querySelector(".toggle").textContent = "+"));
    item.querySelector(".toggle").textContent = "-";
    expandedRow.innerHTML = "";

    filterData[type].forEach((name) => {
      const btn = document.createElement("button");
      btn.type = "button";
      btn.textContent = name;

      if (type === "content") {
        btn.addEventListener("click", () => {
          Object.values(contentForms).forEach(
            (f) => f && (f.style.display = "none")
          );
          if (contentForms[name]) contentForms[name].style.display = "block";
          expandedRow
            .querySelectorAll("button")
            .forEach((b) => b.classList.remove("active"));
          btn.classList.add("active");
        });
      } else {
        btn.addEventListener("click", () => {
          btn.classList.toggle("active");
          if (type === "topic")
            document
              .querySelectorAll(".auto-topic")
              .forEach((el) => (el.value = name));
          if (type === "industry")
            document
              .querySelectorAll(".auto-industry")
              .forEach((el) => (el.value = name));
        });
      }

      expandedRow.appendChild(btn);
    });

    expandedRow.style.display = "grid";
    expandedRow.dataset.type = type;
  });
});

// ✅ Recruit Dropdown Toggle
function toggleRecruitDropdown(event) {
  event.preventDefault();
  const content = event.currentTarget.nextElementSibling;
  const arrow = event.currentTarget.querySelector(".arrow");
  content.style.display = content.style.display === "flex" ? "none" : "flex";
  arrow.classList.toggle("rotate");
}

// ✅ Dynamic Form Adders
function addSection(containerId) {
  const container = document.getElementById(containerId);
  const div = document.createElement("div");
  div.className = "item";
  div.innerHTML = `
    <label>Section Title</label>
    <input type="text" maxlength="100" required>
    <label>Section Description</label>
    <textarea maxlength="350" required></textarea>
  `;
  container.appendChild(div);
}

function addAccordion(containerId) {
  const container = document.getElementById(containerId);
  const div = document.createElement("div");
  div.className = "accordion-item";
  div.innerHTML = `
    <input type="text" placeholder="Accordion Title" maxlength="100" required>
    <textarea placeholder="Accordion Content" maxlength="350" required></textarea>
  `;
  container.appendChild(div);
}

function addInterviewQuestion() {
  const container = document.getElementById("interviewQuestionsContainer");
  const count = container.children.length + 1;
  const div = document.createElement("div");
  div.className = "question-block";
  div.innerHTML = `
    <label>Question ${count}</label>
    <input type="text" maxlength="100" required>
    <label>Answer ${count}</label>
    <textarea maxlength="350" required></textarea>
  `;
  container.appendChild(div);
}

function addListItem(containerId) {
  const container = document.getElementById(containerId);
  const div = document.createElement("div");
  div.className = "list-item";
  div.innerHTML =
    '<input type="text" maxlength="100" placeholder="Bullet point" required>';
  container.appendChild(div);
}

function addEpisode() {
  const container = document.getElementById("episodesContainer");
  const div = document.createElement("div");
  div.className = "episode-block";
  div.innerHTML = `
    <label>Title</label>
    <input type="text" placeholder="Enter title" maxlength="100" required>

    <label>Description</label>
    <textarea placeholder="Enter description..." maxlength="350" required></textarea>

    <label>Date</label>
    <input type="date" required>

    <label>Duration</label>
    <input type="text" placeholder="e.g. 45 minutes 30 seconds" maxlength="100">

    <label>Upload Episode Image</label>
    <input type="file" accept="image/*" required>

    <label>Upload Audio File</label>
<input type="file" accept="audio/*" required onchange="previewAudio(event)">

<!-- Optional: Audio preview -->
<audio id="audioPreview" controls style="display:none; margin-top:10px; width:100%;"></audio>

  `;
  container.appendChild(div);
}
function previewAudio(event) {
  const file = event.target.files[0];
  const audioPreview = document.getElementById("audioPreview");

  if (file) {
    const fileURL = URL.createObjectURL(file);
    audioPreview.src = fileURL;
    audioPreview.style.display = "block";
  } else {
    audioPreview.style.display = "none";
  }
}

function addAuthor() {
  const groups = ["authorGroup", "writerSection"];
  groups.forEach((id) => {
    const container = document.getElementById(id);
    if (container) {
      const div = document.createElement("div");
      div.className = "author-item";
      div.innerHTML = `
        <label>Author Name</label>
        <input type="text" placeholder="Enter author name" maxlength="100" required>

        <label>Designation</label>
        <input type="text" placeholder="Enter designation" maxlength="100" required>

        <label>LinkedIn URL</label>
        <input type="url" placeholder="Enter LinkedIn profile link">
      `;
      container.appendChild(div);
    }
  });
}

function addFinding() {
  const container = document.getElementById("findingsContainer");
  const div = document.createElement("div");
  div.className = "finding-item";
  div.innerHTML =
    '<input type="text" placeholder="Key Finding" maxlength="100" required>';
  container.appendChild(div);
}

function addBrief() {
  const container = document.getElementById("briefContainer");
  const div = document.createElement("div");
  div.className = "item";
  div.innerHTML = `
    <label>Section Content</label>
    <textarea maxlength="350" required></textarea>
    <input type="text" placeholder="Key insight" maxlength="100" required>
  `;
  container.appendChild(div);
}
