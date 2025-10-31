
document.addEventListener("DOMContentLoaded", () => {
  const tableBody = document.getElementById("dashboardBody");

  // Attach to all forms
  document.querySelectorAll("form").forEach(form => {
    form.addEventListener("submit", e => {
      e.preventDefault();

      const formType = form.querySelector("h2")
        ? form.querySelector("h2").textContent.trim()
        : "Unknown";

      const formData = Object.fromEntries(new FormData(form).entries());
      const title = formData.title || formData.articleTitle || "Untitled";
      const topic = formData.topic || "N/A";
      const industry = formData.industry || "N/A";
      const date = formData.date || new Date().toISOString().split("T")[0];
      const status = "Published";

      // Create a new table row
      const newRow = document.createElement("tr");
      newRow.innerHTML = `
        <td>${title}</td>
        <td>${topic}</td>
        <td>${industry}</td>
        <td>${formType}</td>
        <td>${date}</td>
        <td>${status}</td>
        <td>✏ Edit 🗑 Delete</td>
      `;

      // Insert at the top (before first row)
      if (tableBody.firstChild) {
        tableBody.insertBefore(newRow, tableBody.firstChild);
      } else {
        tableBody.appendChild(newRow);
      }

      // Optional animation highlight
      newRow.style.backgroundColor = "#e6f7ff";
      setTimeout(() => newRow.style.backgroundColor = "#fff", 1000);

      // Reset form
      form.reset();

      alert(`✅ ${formType} submitted successfully and added to the top of dashboard!`);
    });
  });
});
