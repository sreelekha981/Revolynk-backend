document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("ResearchForm");
  const urlParams = new URLSearchParams(window.location.search);
  const researchId = urlParams.get("id"); // if present, it's edit mode
  const apiBase = "http://localhost:8080/api/research";

  // ============================================================
  // 🧱 DYNAMIC FIELD FUNCTIONS
  // ============================================================

  window.addAuthor = function () {
    const group = document.getElementById("authorGroup");
    const div = document.createElement("div");
    div.classList.add("author-item");
    div.innerHTML = `
      <label>Author Name</label>
      <input type="text" name="authorNames" maxlength="100" required>

      <label>Designation</label>
      <input type="text" name="authorDesignations" maxlength="100" required>

      <label>LinkedIn URL</label>
      <input type="url" name="authorLinkedIns" placeholder="https://linkedin.com/in/...">
    `;
    group.appendChild(div);
  };

  window.addFinding = function () {
    const container = document.getElementById("findingsContainer");
    const div = document.createElement("div");
    div.classList.add("finding-item");
    div.innerHTML = `
      <input type="text" name="keyFindings" maxlength="100" placeholder="Key Finding" required>
    `;
    container.appendChild(div);
  };

  window.addContentBlock = function () {
    const wrapper = document.getElementById("contentBlockWrapper");
    const div = document.createElement("div");
    div.classList.add("content-block");
    div.innerHTML = `
      <label>Section Title</label>
      <input type="text" name="sectionTitles" maxlength="100" required>

      <label>Content</label>
      <textarea name="sectionContents" maxlength="350" required></textarea>
    `;
    wrapper.appendChild(div);
  };

  window.addAccordion = function () {
    const container = document.getElementById("researchAccordionContainer");
    const div = document.createElement("div");
    div.classList.add("accordion-item");
    div.innerHTML = `
      <input type="text" name="accordionTitles" maxlength="100" placeholder="Accordion Title" required>
      <textarea name="accordionContents" maxlength="350" placeholder="Accordion Content" required></textarea>
    `;
    container.appendChild(div);
  };

  // ============================================================
  // ✏ LOAD RESEARCH DATA (EDIT MODE)
  // ============================================================

  if (researchId) {
    loadResearchDetails(researchId);
  }

  async function loadResearchDetails(id) {
    try {
      const res = await fetch(`${apiBase}/${id}`);
      if (!res.ok) throw new Error("Failed to fetch research data");
      const data = await res.json();

      // Fill form fields
      form.querySelector('[name="title"]').value = data.title || "";
      form.querySelector('[name="topic"]').value = data.topic || "";
      form.querySelector('[name="industry"]').value = data.industry || "";
      form.querySelector('[name="date"]').value = data.date || "";
      form.querySelector('[name="abstractText"]').value = data.abstractText || "";

      // Load authors dynamically
      const group = document.getElementById("authorGroup");
      group.innerHTML = "";
      if (data.authors && data.authors.length > 0) {
        data.authors.forEach(author => {
          const div = document.createElement("div");
          div.classList.add("author-item");
          div.innerHTML = `
            <label>Author Name</label>
            <input type="text" name="authorNames" maxlength="100" value="${author.name}" required>
            <label>Designation</label>
            <input type="text" name="authorDesignations" maxlength="100" value="${author.designation}" required>
            <label>LinkedIn URL</label>
            <input type="url" name="authorLinkedIns" value="${author.linkedin || ""}">
          `;
          group.appendChild(div);
        });
      }

      // Load findings dynamically
      const container = document.getElementById("findingsContainer");
      container.innerHTML = "";
      if (data.keyFindings && data.keyFindings.length > 0) {
        data.keyFindings.forEach(finding => {
          const div = document.createElement("div");
          div.classList.add("finding-item");
          div.innerHTML = `
            <input type="text" name="keyFindings" maxlength="100" value="${finding.text}" required>
          `;
          container.appendChild(div);
        });
      }

      // Load content blocks (optional)
      const contentWrapper = document.getElementById("contentBlockWrapper");
      contentWrapper.innerHTML = "";
      if (data.sections && data.sections.length > 0) {
        data.sections.forEach(section => {
          const div = document.createElement("div");
          div.classList.add("content-block");
          div.innerHTML = `
            <label>Section Title</label>
            <input type="text" name="sectionTitles" value="${section.title}" required>
            <label>Content</label>
            <textarea name="sectionContents" required>${section.content}</textarea>
          `;
          contentWrapper.appendChild(div);
        });
      }

      // Load accordion data (optional)
      const accContainer = document.getElementById("researchAccordionContainer");
      accContainer.innerHTML = "";
      if (data.accordions && data.accordions.length > 0) {
        data.accordions.forEach(acc => {
          const div = document.createElement("div");
          div.classList.add("accordion-item");
          div.innerHTML = `
            <input type="text" name="accordionTitles" value="${acc.title}" required>
            <textarea name="accordionContents" required>${acc.content}</textarea>
          `;
          accContainer.appendChild(div);
        });
      }

    } catch (err) {
      console.error("❌ Error loading research details:", err);
      alert("Failed to load research details.");
    }
  }

  // ============================================================
  // 💾 SUBMIT FORM (CREATE / UPDATE)
  // ============================================================

  form.addEventListener("submit", async function (event) {
    event.preventDefault();
    const formData = new FormData(form);
    const method = researchId ? "PUT" : "POST";
    const url = researchId ? `${apiBase}/${researchId}` : apiBase;

    try {
      const response = await fetch(url, {
        method: method,
        body: formData
      });

      if (!response.ok) {
        throw new Error("Server responded with " + response.status);
      }

      const result = await response.json();
      if (researchId) {
        alert("✅ Research updated successfully!");
      } else {
        alert("✅ Research submitted successfully!\nID: " + result.id);
      }

      window.location.href = "dashboard.html"; // redirect to list page

    } catch (error) {
      console.error("❌ Failed to save research:", error);
      alert("Failed to save research. Check console for details.");
    }
  });

  // ============================================================
  // 🗑 DELETE RESEARCH
  // ============================================================

  window.deleteResearch = async function (id) {
    if (!confirm("Are you sure you want to delete this research?")) return;

    try {
      const res = await fetch(`${apiBase}/${id}`, { method: "DELETE" });
      if (res.ok) {
        alert("✅ Research deleted successfully!");
        window.location.reload();
      } else {
        alert("❌ Failed to delete research. Server returned " + res.status);
      }
    } catch (err) {
      console.error("Error deleting research:", err);
      alert("Error deleting research.");
    }
  };
});
