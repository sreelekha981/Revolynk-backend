const API_URL = "http://localhost:8080/api/jobs";
let jobList = []; // store the data in memory

async function loadJobs() {
  try {
    const res = await fetch(API_URL);
    if (!res.ok) throw new Error("Failed to fetch jobs");
    jobList = await res.json(); // store data globally

    const container = document.getElementById("jobsContainer"); // your grid/row wrapper
    container.innerHTML = "";

    if (jobList.length === 0) {
      container.innerHTML = `<div class="col-12 text-center"><p>No job postings available.</p></div>`;
      return;
    }

    jobList.forEach(job => {
      const card = document.createElement("div");
      card.className = "col-md-4 col-sm-6";

      card.innerHTML = `
        <div class="job-card">
          <i class="ri-${job.icon || "briefcase-fill"} job-icon"></i>
          <h5 class="job-title">${job.title}</h5>
          <div class="job-details">
            <span>Experience: <strong>${job.experience || "N/A"}</strong></span>
            <span>Vacancies: <strong>${job.vacancies || "N/A"}</strong></span>
          </div>
          <a href="../dashboard/job-posting-main.html?id=${job.id}" class="more-details">
            More details <i class="ri-arrow-right-line"></i>
          </a>
        </div>
      `;

      container.appendChild(card);
    });
  } catch (err) {
    console.error("Error loading jobs:", err);
  }
}

async function deleteJob(id) {
  if (!confirm("Are you sure to delete this job?")) return;
  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  await loadJobs(); // refresh cards
}

window.addEventListener("DOMContentLoaded", loadJobs);
