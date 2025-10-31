const API_URL = "http://localhost:8080/api/jobs";

async function loadJobs() {
  const res = await fetch(API_URL);
  const jobs = await res.json();

  const tbody = document.querySelector("#jobsTable tbody");
  tbody.innerHTML = "";

  if (jobs.length === 0) {
    tbody.innerHTML = `<tr><td colspan="6" style="text-align:center;">No job postings available.</td></tr>`;
    return;
  }

  jobs.forEach(job => {
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${job.title}</td>
      <td>${job.experience}</td>
      <td>${job.type}</td>
      <td>${job.date}</td>
      <td>${job.status}</td>
      <td>
        <a href="job-posting-main.html?id=${job.id}">View</a> |
        <a href="job-posting-form.html?id=${job.id}">Edit</a> |
        <button onclick="deleteJob(${job.id})">Delete</button>
      </td>
    `;
    tbody.appendChild(tr);
  });
}

async function deleteJob(id) {
  if (!confirm("Are you sure to delete this job?")) return;
  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  loadJobs();
}

window.addEventListener("DOMContentLoaded", loadJobs);
