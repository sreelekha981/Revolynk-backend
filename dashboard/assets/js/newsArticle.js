document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("NewsArticleForm");
    if (!form) return;

    // --- Add dynamic inputs ---
    document.getElementById("addBulletPoint").addEventListener("click", () => {
      const container = document.getElementById("bulletPointsContainer");
      const input = document.createElement("input");
      input.type = "text";
      input.name = "bulletPoints";
      input.placeholder = "Enter bullet point";
      input.classList.add("form-control", "mt-2");
      container.appendChild(input);
    });

    document.getElementById("addSection").addEventListener("click", () => {
      const container = document.getElementById("sectionsContainer");
      const section = document.createElement("div");
      section.classList.add("section-block", "mt-3");
      section.innerHTML = `
        <input type="text" name="sectionTitle" placeholder="Section Title" class="form-control mb-2">
        <textarea name="sectionContent" placeholder="Section Content" class="form-control"></textarea>
      `;
      container.appendChild(section);
    });

    document.getElementById("addAccordion").addEventListener("click", () => {
      const container = document.getElementById("accordionContainer");
      const accordion = document.createElement("div");
      accordion.classList.add("accordion-block", "mt-3");
      accordion.innerHTML = `
        <input type="text" name="accordionTitle" placeholder="Accordion Title" class="form-control mb-2">
        <textarea name="accordionContent" placeholder="Accordion Content" class="form-control"></textarea>
      `;
      container.appendChild(accordion);
    });

    document.getElementById("addAuthor").addEventListener("click", () => {
      const container = document.getElementById("authorsContainer");
      const author = document.createElement("div");
      author.classList.add("author-block", "mt-3");
      author.innerHTML = `
        <input type="text" name="authorName" placeholder="Author Name" class="form-control mb-2">
        <input type="text" name="designation" placeholder="Designation" class="form-control mb-2">
        <input type="url" name="linkedinUrl" placeholder="LinkedIn URL" class="form-control">
      `;
      container.appendChild(author);
    });

    // --- Handle Form Submit ---
    form.addEventListener("submit", async (e) => {
      e.preventDefault();

      const data = {
        articleTitle: form.articleTitle.value.trim(),
        subtitle: form.subtitle.value.trim(),
        topic: form.topic.value.trim(),
        industry: form.industry.value.trim(),
        date: form.date.value,
        mainContent: form.mainContent.value.trim(),
        heading: form.heading.value.trim(),
        description: form.description.value.trim(),
        bulletPoints: [...form.querySelectorAll('[name="bulletPoints"]')].map(i => i.value.trim()).filter(Boolean),
        sectionTitle: [...form.querySelectorAll('[name="sectionTitle"]')].map(i => i.value.trim()).filter(Boolean),
        sectionContent: [...form.querySelectorAll('[name="sectionContent"]')].map(i => i.value.trim()).filter(Boolean),
        accordionTitle: [...form.querySelectorAll('[name="accordionTitle"]')].map(i => i.value.trim()).filter(Boolean),
        accordionContent: [...form.querySelectorAll('[name="accordionContent"]')].map(i => i.value.trim()).filter(Boolean),
        authorName: [...form.querySelectorAll('[name="authorName"]')].map(i => i.value.trim()).filter(Boolean),
        designation: [...form.querySelectorAll('[name="designation"]')].map(i => i.value.trim()).filter(Boolean),
        linkedinUrl: [...form.querySelectorAll('[name="linkedinUrl"]')].map(i => i.value.trim()).filter(Boolean)
      };

      // ✅ Check required fields
      if (!data.articleTitle || !data.date || !data.mainContent) {
        alert("⚠️ Please fill in all required fields (Title, Date, and Main Content).");
        return;
      }

      const fd = new FormData();
      fd.append("article", new Blob([JSON.stringify(data)], { type: "application/json" }));

      const imageFile = form.querySelector('[name="image"]').files[0];
      if (imageFile) fd.append("image", imageFile);

      try {
        const res = await fetch("http://localhost:8080/api/news", {
          method: "POST",
          body: fd
        });

        if (!res.ok) throw new Error(await res.text());

        const msg = await res.text();
        alert("✅ " + msg);
        form.reset();
        document.getElementById("bulletPointsContainer").innerHTML = "";
        document.getElementById("sectionsContainer").innerHTML = "";
        document.getElementById("accordionContainer").innerHTML = "";
        document.getElementById("authorsContainer").innerHTML = "";

      } catch (err) {
        console.error("Submit error:", err);
        alert("❌ Error saving article: " + err.message);
      }
    });
  });