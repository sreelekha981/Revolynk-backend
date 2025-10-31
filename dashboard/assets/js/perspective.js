
    // --- Add Dynamic Blocks ---
    function addBrief() {
      const container = document.getElementById('briefContainer');
      const div = document.createElement('div');
      div.classList.add('item');
      div.innerHTML = `
        <label>Section Content</label>
        <textarea name="briefSectionContent" maxlength="350" required></textarea>
        <input type="text" name="briefKeyInsight" maxlength="100" placeholder="Key insight" required>
      `;
      container.appendChild(div);
    }

    function addQuestionBlock() {
      const container = document.getElementById('questionContainer');
      const div = document.createElement('div');
      div.classList.add('question-item');
      div.innerHTML = `
        <label>Title</label>
        <input type="text" name="sectionTitle" maxlength="100" required>
        <label>Content</label>
        <textarea name="sectionContent" maxlength="350" required></textarea>
      `;
      container.appendChild(div);
    }

    function addAccordion() {
      const container = document.getElementById('accordionContainer');
      const div = document.createElement('div');
      div.classList.add('item');
      div.innerHTML = `
        <label>Accordion Title</label>
        <input type="text" name="accordionTitle" maxlength="100" required>
        <label>Accordion Description</label>
        <textarea name="accordionDescription" maxlength="350" required></textarea>
      `;
      container.appendChild(div);
    }

    // --- Handle Form Submission ---
    document.getElementById('PerspectiveForm').addEventListener('submit', async function(e) {
      e.preventDefault();

      const form = e.target;
      const perspective = {
        articleTitle: form.articleTitle.value.trim(),
        subtitle: form.subtitle.value.trim(),
        topic: form.topic.value.trim(),
        industry: form.industry.value.trim(),
        date: form.date.value,
        briefSectionContent: [],
        briefKeyInsight: [],
        sectionTitle: [],
        sectionContent: [],
        accordionTitle: [],
        accordionDescription: []
      };

      // Collect all dynamic inputs
      form.querySelectorAll('textarea[name="briefSectionContent"]').forEach(el => perspective.briefSectionContent.push(el.value.trim()));
      form.querySelectorAll('input[name="briefKeyInsight"]').forEach(el => perspective.briefKeyInsight.push(el.value.trim()));
      form.querySelectorAll('input[name="sectionTitle"]').forEach(el => perspective.sectionTitle.push(el.value.trim()));
      form.querySelectorAll('textarea[name="sectionContent"]').forEach(el => perspective.sectionContent.push(el.value.trim()));
      form.querySelectorAll('input[name="accordionTitle"]').forEach(el => perspective.accordionTitle.push(el.value.trim()));
      form.querySelectorAll('textarea[name="accordionDescription"]').forEach(el => perspective.accordionDescription.push(el.value.trim()));

      console.log("📦 Final JSON:", JSON.stringify(perspective, null, 2));

      const fd = new FormData();
      fd.append("perspective", new Blob([JSON.stringify(perspective)], { type: "application/json" }));

      const imageFile = form.image.files[0];
      if (imageFile) fd.append("image", imageFile);

      try {
        const res = await fetch("http://localhost:8080/api/perspectives", {
          method: "POST",
          body: fd
        });

        if (!res.ok) {
          const errText = await res.text();
          throw new Error(errText);
        }

        const saved = await res.text();
        alert("✅ Perspective saved successfully!\n\n" + saved);
        form.reset();
      } catch (err) {
        console.error("❌ Error:", err);
        alert("❌ Submission failed: " + err.message);
      }
    });
  