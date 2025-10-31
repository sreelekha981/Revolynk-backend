
const API_URL = "http://localhost:8080/api/jobs";

async function loadJobDetails() {
  const jobId = new URLSearchParams(window.location.search).get("id");
  if (!jobId) {
    document.querySelector(".container").innerHTML = "<p>No job selected.</p>";
    return;
  }

  try {
    const res = await fetch(`${API_URL}/${jobId}`);
    if (!res.ok) throw new Error("Job not found");
    const job = await res.json();

    document.getElementById("jobTitle").textContent = job.title;
    document.getElementById("jobExperience").textContent = job.experience;
    document.getElementById("jobLocation").textContent = job.location;
    document.getElementById("jobType").textContent = job.type;
    document.getElementById("jobDescription").textContent = job.description;

    // ✅ Render profile points
    const profileList = document.getElementById("profileList");
    profileList.innerHTML = "";
    try {
      const profilePoints = JSON.parse(job.profilePoints || "[]");
      if (Array.isArray(profilePoints)) {
        profilePoints.forEach(p => {
          const li = document.createElement("li");
          li.textContent = p;
          profileList.appendChild(li);
        });
      }
    } catch (err) {
      console.warn("Invalid profilePoints JSON:", err);
    }

    // ✅ Render skills
    const skillsList = document.getElementById("skillsList");
    skillsList.innerHTML = "";
    try {
      const skills = JSON.parse(job.skills || "[]");
      if (Array.isArray(skills)) {
        skills.forEach(s => {
          const li = document.createElement("li");
          li.textContent = s;
          skillsList.appendChild(li);
        });
      }
    } catch (err) {
      console.warn("Invalid skills JSON:", err);
    }

  } catch (err) {
    console.error(err);
    document.querySelector(".container").innerHTML = "<p>Unable to load job details.</p>";
  }
}

window.addEventListener("DOMContentLoaded", loadJobDetails);

