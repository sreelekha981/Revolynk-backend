
// const API_URL = "http://localhost:8080/api/jobs";

// async function loadJobDetails() {
//   const jobId = new URLSearchParams(window.location.search).get("id");
//   if (!jobId) {
//     document.querySelector(".container").innerHTML = "<p>No job selected.</p>";
//     return;
//   }

//   try {
//     const res = await fetch(`${API_URL}/${jobId}`);
//     if (!res.ok) throw new Error("Job not found");
//     const job = await res.json();

//     document.getElementById("jobTitle").textContent = job.title;
//     document.getElementById("jobExperience").textContent = job.experience;
//     document.getElementById("jobLocation").textContent = job.location;
//     document.getElementById("jobType").textContent = job.type;
//     document.getElementById("jobDescription").textContent = job.description;

//     // ✅ Render profile points
//     const profileList = document.getElementById("profileList");
//     profileList.innerHTML = "";
//     try {
//       const profilePoints = JSON.parse(job.profilePoints || "[]");
//       if (Array.isArray(profilePoints)) {
//         profilePoints.forEach(p => {
//           const li = document.createElement("li");
//           li.textContent = p;
//           profileList.appendChild(li);
//         });
//       }
//     } catch (err) {
//       console.warn("Invalid profilePoints JSON:", err);
//     }

//     // ✅ Render skills
//     const skillsList = document.getElementById("skillsList");
//     skillsList.innerHTML = "";
//     try {
//       const skills = JSON.parse(job.skills || "[]");
//       if (Array.isArray(skills)) {
//         skills.forEach(s => {
//           const li = document.createElement("li");
//           li.textContent = s;
//           skillsList.appendChild(li);
//         });
//       }
//     } catch (err) {
//       console.warn("Invalid skills JSON:", err);
//     }

//   } catch (err) {
//     console.error(err);
//     document.querySelector(".container").innerHTML = "<p>Unable to load job details.</p>";
//   }
// }

// window.addEventListener("DOMContentLoaded", loadJobDetails);

const API_URL = "http://localhost:8080/api/jobs";

async function loadJobDetails() {
  const jobId = new URLSearchParams(window.location.search).get("id");
  const container = document.querySelector(".container");

  // ✅ No job ID provided
  if (!jobId) {
    container.innerHTML = "<p>No job selected.</p>";
    return;
  }

  try {
    const res = await fetch(`${API_URL}/${jobId}`);
    if (!res.ok) throw new Error("Job not found");

    const job = await res.json();

    // ✅ Basic job info
    document.getElementById("jobTitle").textContent = job.title || "Untitled Job";
    document.getElementById("jobExperience").textContent = job.experience || "N/A";
    document.getElementById("jobLocation").textContent = job.location || "N/A";
    document.getElementById("jobType").textContent = job.type || "N/A";
    document.getElementById("jobDescription").textContent = job.description || "No description available.";

    // ✅ Helper: safely parse array or comma-separated values
    const safeParseList = (value) => {
      let arr = [];
      try {
        const parsed = JSON.parse(value);
        if (Array.isArray(parsed)) {
          arr = parsed;
        } else if (typeof parsed === "string") {
          arr = parsed.split(",").map(s => s.trim()).filter(Boolean);
        }
      } catch {
        if (typeof value === "string") {
          arr = value.split(",").map(s => s.trim()).filter(Boolean);
        }
      }
      return arr;
    };

    // ✅ Render profile points
    const profileList = document.getElementById("profileList");
    profileList.innerHTML = "";
    const profilePoints = safeParseList(job.profilePoints);
    if (profilePoints.length > 0) {
      profilePoints.forEach(p => {
        const li = document.createElement("li");
        li.textContent = p;
        profileList.appendChild(li);
      });
    } else {
      const li = document.createElement("li");
      li.textContent = "No profile points available.";
      profileList.appendChild(li);
    }

    // ✅ Render skills
    const skillsList = document.getElementById("skillsList");
    skillsList.innerHTML = "";
    const skills = safeParseList(job.skills);
    if (skills.length > 0) {
      skills.forEach(s => {
        const li = document.createElement("li");
        li.textContent = s;
        skillsList.appendChild(li);
      });
    } else {
      const li = document.createElement("li");
      li.textContent = "No skills listed.";
      skillsList.appendChild(li);
    }

  } catch (err) {
    console.error("❌ Error loading job details:", err);
    container.innerHTML = "<p>Unable to load job details. Please try again later.</p>";
  }
}

window.addEventListener("DOMContentLoaded", loadJobDetails);
