
// // let episodeCount = 1;

// // function addEpisode() {
// //   const container = document.getElementById("episodesContainer");
// //   const div = document.createElement("div");
// //   div.classList.add("episode-item");
// //   div.innerHTML = `
// //     <hr>
// //     <label>Episode Title</label>
// //     <input type="text" name="episodeTitle" maxlength="100" placeholder="Episode title" required>
// //     <label>Description</label>
// //     <textarea name="episodeDescription" maxlength="350" placeholder="Episode description" required></textarea>
// //     <label>Audio File</label>
// //     <input type="file" name="audioFile" accept="audio/*" required>
// //     <button type="button" class="remove-btn" onclick="removeEpisode(this)">Remove Episode</button>
// //   `;
// //   container.appendChild(div);
// // }

// // function removeEpisode(button) {
// //   button.parentElement.remove();
// // }

// // document.getElementById("PodcastForm").addEventListener("submit", async function(event) {
// //   event.preventDefault();

// //   const form = event.target;
// //   const formData = new FormData();

// //   const podcast = {
// //     title: form.title.value.trim(),
// //     subtitle: form.subtitle.value.trim(),
// //     topic: form.topic.value.trim(),
// //     industry: form.industry.value.trim(),
// //     date: form.date.value
// //   };

// //   const episodeItems = document.querySelectorAll(".episode-item");
// //   const episodes = [];

// //   episodeItems.forEach((item, index) => {
// //     const title = item.querySelector('input[name="episodeTitle"]').value.trim();
// //     const description = item.querySelector('textarea[name="episodeDescription"]').value.trim();
// //     const audioFile = item.querySelector('input[name="audioFile"]').files[0];
// //     episodes.push({ episodeTitle: title, description });
// //     formData.append(`audioFile${index}`, audioFile);
// //   });

// //   formData.append("podcast", new Blob([JSON.stringify({ ...podcast, episodes })], { type: "application/json" }));

// //   try {
// //     const response = await fetch("http://localhost:8080/api/podcasts", {
// //       method: "POST",
// //       body: formData
// //     });
// //     const msg = await response.text();
// //     alert(response.ok ? "✅ Podcast submitted successfully!" : "❌ " + msg);
// //   } catch (e) {
// //     console.error(e);
// //     alert("❌ Failed to submit podcast.");
// //   }
// // });


// let episodeCount = 1;

// function addEpisode() {
//   const container = document.getElementById("episodesContainer");
//   const div = document.createElement("div");
//   div.classList.add("episode-item");
//   div.innerHTML = `
//     <hr>
//     <label>Episode Title</label>
//     <input type="text" name="episodeTitle" maxlength="100" placeholder="Episode title" required>
//     <label>Description</label>
//     <textarea name="episodeDescription" maxlength="350" placeholder="Episode description" required></textarea>
//     <label>Audio File</label>
//     <input type="file" name="audioFile" accept="audio/*" required>
//     <button type="button" class="remove-btn" onclick="removeEpisode(this)">Remove Episode</button>
//   `;
//   container.appendChild(div);
// }

// function removeEpisode(button) {
//   button.parentElement.remove();
// }

// document.getElementById("PodcastForm").addEventListener("submit", async function(event) {
//   event.preventDefault();

//   const form = event.target;
//   const formData = new FormData();

//   const podcast = {
//     title: form.title.value.trim(),
//     subtitle: form.subtitle.value.trim(),
//     topic: form.topic.value.trim(),
//     industry: form.industry.value.trim(),
//     date: form.date.value
//   };

//   const episodeItems = document.querySelectorAll(".episode-item");
//   const episodes = [];

//   episodeItems.forEach((item, index) => {
//     const title = item.querySelector('input[name="episodeTitle"]').value.trim();
//     const description = item.querySelector('textarea[name="episodeDescription"]').value.trim();
//     const audioFile = item.querySelector('input[name="audioFile"]').files[0];
//     episodes.push({ episodeTitle: title, description });
//     formData.append(`audioFile${index}`, audioFile);
//   });

//   formData.append("podcast", new Blob([JSON.stringify({ ...podcast, episodes })], { type: "application/json" }));

//   try {
//     const response = await fetch("http://localhost:8080/api/podcasts", {
//       method: "POST",
//       body: formData
//     });
//     const msg = await response.text();

//     if (response.ok) {
//       alert("✅ Podcast submitted successfully!");
//       // 👇 Redirect to your dashboard page
//       window.location.href = "/dashboard.html";  // change this to your actual dashboard file
//     } else {
//       alert("❌ " + msg);
//     }
//   } catch (e) {
//     console.error(e);
//     alert("❌ Failed to submit podcast.");
//   }
// });

let editingPodcastId = null; // null = create mode, not editing
let episodeCount = 1;

function addEpisode() {
  const container = document.getElementById("episodesContainer");
  const div = document.createElement("div");
  div.classList.add("episode-item");
  div.innerHTML = `
    <hr>
    <label>Episode Title</label>
    <input type="text" name="episodeTitle" maxlength="100" placeholder="Episode title" required>
    <label>Description</label>
    <textarea name="episodeDescription" maxlength="350" placeholder="Episode description" required></textarea>
    <label>Audio File</label>
    <input type="file" name="audioFile" accept="audio/*" required>
    <button type="button" class="remove-btn" onclick="removeEpisode(this)">Remove Episode</button>
  `;
  container.appendChild(div);
}

function removeEpisode(button) {
  button.parentElement.remove();
}

document.getElementById("PodcastForm").addEventListener("submit", async function (event) {
  event.preventDefault();

  const form = event.target;
  const formData = new FormData();

  const podcast = {
    title: form.title.value.trim(),
    subtitle: form.subtitle.value.trim(),
    topic: form.topic.value.trim(),
    industry: form.industry.value.trim(),
    date: form.date.value,
  };

  const episodeItems = document.querySelectorAll(".episode-item");
  const episodes = [];

  episodeItems.forEach((item, index) => {
    const title = item.querySelector('input[name="episodeTitle"]').value.trim();
    const description = item.querySelector('textarea[name="episodeDescription"]').value.trim();
    const audioFile = item.querySelector('input[name="audioFile"]').files[0];
    episodes.push({ episodeTitle: title, description });
    if (audioFile) {
      formData.append(`audioFile${index}`, audioFile);
    }
  });

  formData.append("podcast", new Blob([JSON.stringify({ ...podcast, episodes })], { type: "application/json" }));

  try {
    const url = editingPodcastId
      ? `http://localhost:8080/api/podcasts/${Id}`
      : "http://localhost:8080/api/podcasts";

    const method = editingPodcastId ? "PUT" : "POST";

    const response = await fetch(url, { method, body: formData });
    const msg = await response.text();

    if (response.ok) {
      alert(editingPodcastId ? "✅ Podcast updated successfully!" : "✅ Podcast created successfully!");
      window.location.href = "/dashboard.html";
    } else {
      alert("❌ " + msg);
    }
  } catch (e) {
    console.error(e);
    alert("❌ Failed to save podcast.");
  }
});

async function loadPodcasts() {
  const tableBody = document.querySelector("#insightsTable tbody");
  if (!tableBody) return;

  tableBody.innerHTML = `<tr><td colspan="8" style="text-align:center;">Loading...</td></tr>`;

  try {
    const res = await fetch("http://localhost:8080/api/podcasts");
    if (!res.ok) throw new Error("Failed to load podcasts");

    const data = await res.json();
    console.log("🎧 Loaded Podcasts:", data);

    tableBody.innerHTML = "";

    if (!Array.isArray(data) || data.length === 0) {
      tableBody.innerHTML = `<tr><td colspan="8" style="text-align:center;">No podcasts found.</td></tr>`;
      return;
    }

    data.forEach((podcast, index) => {
      tableBody.innerHTML += `
        <tr>
          <td>${index + 1} (#${podcast.id || "-"})</td>
          <td>${podcast.title || "-"}</td>
          <td>${podcast.topic || "-"}</td>
          <td>${podcast.industry || "-"}</td>
          <td>${podcast.date || "-"}</td>
          <td>${podcast.episodes?.length || 0}</td>
          <td>
            <button onclick="editPodcast(${podcast.id})">✏️ Edit</button>
            <button onclick="deletePodcast(${podcast.id})">🗑 Delete</button>
          </td>
        </tr>
      `;
    });
  } catch (err) {
    console.error("❌ Error loading podcasts:", err);
    tableBody.innerHTML = `<tr><td colspan="8" style="text-align:center; color:red;">Failed to load data</td></tr>`;
  }
}

async function deletePodcast(id) {
  if (!confirm("Are you sure you want to delete this podcast?")) return;

  try {
    const res = await fetch(`http://localhost:8080/api/podcasts/${id}`, { method: "DELETE" });
    if (!res.ok) throw new Error("Failed to delete podcast");
    alert("🗑 Podcast deleted successfully");
    loadPodcasts();
  } catch (err) {
    console.error("❌ Error deleting podcast:", err);
    alert("Failed to delete podcast.");
  }
}

function editPodcast(id) {
  // Save id in localStorage and redirect to edit form
  localStorage.setItem("editingPodcastId", id);
  window.location.href = "/dashboard/add-podcast.html";
}

document.addEventListener("DOMContentLoaded", loadPodcasts);
