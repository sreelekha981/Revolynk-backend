document.addEventListener("DOMContentLoaded", () => {
  // Run each section loader safely so one failure doesn’t stop others
  safeLoad(loadInterviews);
  safeLoad(loadBlogs);
  safeLoad(loadPerspectives);
  safeLoad(loadResearchReports);
  safeLoad(loadNewsArticles);
  safeLoad(loadPodcasts);
});

/* -------------------------------------------------------------------------- */
/* 🛡 SAFE WRAPPER */
/* -------------------------------------------------------------------------- */
async function safeLoad(fn) {
  try {
    await fn();
  } catch (err) {
    console.error(`❌ Error in ${fn.name}:`, err);
  }
}

/* -------------------------------------------------------------------------- */
/* 🟦 INTERVIEWS */
/* -------------------------------------------------------------------------- */
async function loadInterviews() {
  const tableBody = document.querySelector("#insightsTable tbody");
  if (!tableBody) return;

  try {
    const res = await fetch("http://localhost:8080/api/live-interviews");
    if (!res.ok) throw new Error("Failed to fetch interviews");

    const data = await res.json();
    tableBody.innerHTML = "";

    if (!data.length) {
      tableBody.innerHTML = `<tr><td colspan="7">No interviews available.</td></tr>`;
      return;
    }

    data.forEach((item) => {
      tableBody.innerHTML += `
        <tr>
          <td>${item.interviewTitle || "-"}</td>
          <td>${item.topic || "-"}</td>
          <td>${item.industry || "-"}</td>
          <td>Live Interview</td>
          <td>${item.date || "-"}</td>
          <td><span class="status">Published</span></td>
          <td>
            <button onclick="viewInterview(${item.id})">✏ Edit</button>
            <button onclick="deleteInterview(${item.id})">🗑 Delete</button>
          </td>
        </tr>`;
    });
  } catch (err) {
    console.error("Error loading interviews:", err);
  }
}

function viewInterview(id) {
  window.location.href = `form.html?type=live-interview&id=${id}`;
}

async function deleteInterview(id) {
  if (!confirm("Delete this interview?")) return;
  await fetch(`http://localhost:8080/api/live-interviews/${id}`, { method: "DELETE" });
  loadInterviews();
}

/* -------------------------------------------------------------------------- */
/* 🟩 BLOGS */
/* -------------------------------------------------------------------------- */
// async function loadBlogs() {
//   const tableBody = document.querySelector("#insightsTable tbody");
//   if (!tableBody) return;

//   try {
//     const res = await fetch("http://localhost:8080/api/blogs/add");
//     if (!res.ok) throw new Error("Failed to fetch blogs");

//     const blogs = await res.json();
//     tableBody.innerHTML = "";

//     if (!blogs.length) {
//       tableBody.innerHTML = `<tr><td colspan="7">No blogs found.</td></tr>`;
//       return;
//     }

//     blogs.forEach((b) => {
//       tableBody.innerHTML += `
//         <tr>
//           <td>${b.title || "-"}</td>
//           <td>${b.topic || "-"}</td>
//           <td>${b.industry || "-"}</td>
//           <td>Blog</td>
//           <td>${b.date || "-"}</td>
//           <td><span class="status">Published</span></td>
//           <td>
//             <button onclick="editBlog(${b.id})">✏ Edit</button>
//             <button onclick="deleteBlog(${b.id})">🗑 Delete</button>
//           </td>
//         </tr>`;
//     });
//   } catch (err) {
//     console.error("Error loading blogs:", err);
//   }
// }

// function editBlog(id) {
//   window.location.href = `form.html?type=blog&id=${id}`;
// }

// async function deleteBlog(id) {
//   if (!confirm("Delete this blog?")) return;
//   await fetch(`http://localhost:8080/api/blogs/add/${id}`, { method: "DELETE" });
//   loadBlogs();
// }

// <!-- ✅ Paste this here -->
// =====================
// Handle Form Submission
// =====================
document.addEventListener("DOMContentLoaded", function () {
  const forms = document.querySelectorAll("form");

  forms.forEach((form) => {
    form.addEventListener("submit", async (e) => {
      e.preventDefault();

      const formData = new FormData(form);
      const data = Object.fromEntries(formData.entries());
      const contentType = form.querySelector("h2")?.textContent || "Unknown";

      data.contentType = contentType;
      data.postedOn = new Date().toISOString().split("T")[0];
      data.status = "Published";

      try {
        const res = await fetch("http://localhost:8080/api/blogs/add", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data),
        });

        if (res.ok) {
          const saved = await res.json();
          alert("✅ Blog added successfully!");
          addBlogToTable(saved);
          form.reset();
        } else {
          const msg = await res.text();
          alert("❌ Failed to add blog: " + msg);
        }
      } catch (err) {
        console.error("Add blog error:", err);
        alert("Server error: " + err.message);
      }
    });
  });

  loadAllBlogs();
});

// =====================
// Load All Blogs
// =====================
async function loadAllBlogs() {
  try {
    const res = await fetch("http://localhost:8080/api/blogs/add");
    const blogs = await res.json();

    const tableBody = document.querySelector("#insightsTable tbody");
    tableBody.innerHTML = "";

    blogs.forEach(addBlogToTable);
  } catch (err) {
    console.error("Load blogs error:", err);
  }
}

// =====================
// Add Blog Row to Table (Top Insert)
// =====================
function addBlogToTable(blog) {
  const tableBody = document.querySelector("#insightsTable tbody");
  const newRow = document.createElement("tr");

  newRow.innerHTML = `
    <tr>
//           <td>${b.title || "-"}</td>
//           <td>${b.topic || "-"}</td>
//           <td>${b.industry || "-"}</td>
//           <td>Blog</td>
//           <td>${b.date || "-"}</td>
//           <td><span class="status">Published</span></td>
//           <td>
//             <button onclick="editBlog(${b.id})">✏ Edit</button>
//             <button onclick="deleteBlog(${b.id})">🗑 Delete</button>
//           </td>
//         </tr>`;

  // Insert at top (first row)
  tableBody.prepend(newRow);
}

// =====================
// Delete Blog Function (Fixed)
// =====================
async function deleteBlog(id) {
  if (!confirm("🗑️ Are you sure you want to delete this blog?")) return;

  try {
    const res = await fetch(`http://localhost:8080/api/blogs/add/${id}`, {
      method: "DELETE",
    });

    if (res.ok) {
      alert("🗑️ Blog deleted successfully!");
      const btn = document.querySelector(
        `#insightsTable button[onclick="deleteBlog(${id})"]`
      );
      if (btn) btn.closest("tr").remove();
    } else {
      const msg = await res.text();
      alert("❌ Failed to delete blog: " + msg);
    }
  } catch (err) {
    console.error("Delete error:", err);
    alert("Server error: " + err.message);
  }
}

// =====================
// Edit Blog (Optional)
// =====================
function editBlog(id) {
  alert("✏ Edit functionality coming soon! (ID: " + id + ")");
}


/* -------------------------------------------------------------------------- */
/* 🟧 PERSPECTIVES */
/* -------------------------------------------------------------------------- */
async function loadPerspectives() {
  const tableBody = document.querySelector("#insightsTable tbody");
  if (!tableBody) return;

  try {
    const res = await fetch("http://localhost:8080/api/perspectives");
    if (!res.ok) throw new Error("Failed to fetch perspectives");

    const data = await res.json();
    tableBody.innerHTML = "";

    if (!data.length) {
      tableBody.innerHTML = `<tr><td colspan="7">No perspectives found.</td></tr>`;
      return;
    }

    data.forEach((p) => {
      tableBody.innerHTML += `
        <tr>
          <td>${p.title || p.articleTitle || "-"}</td>
          <td>${p.topic || "-"}</td>
          <td>${p.industry || "-"}</td>
          <td>Perspective</td>
          <td>${p.date || "-"}</td>
          <td><span class="status">Published</span></td>
          <td>
            <button onclick="editPerspective(${p.id})">✏ Edit</button>
            <button onclick="deletePerspective(${p.id})">🗑 Delete</button>
          </td>
        </tr>`;
    });
  } catch (err) {
    console.error("Error loading perspectives:", err);
  }
}

function editPerspective(id) {
  window.location.href = `form.html?type=perspective&id=${id}`;
}

async function deletePerspective(id) {
  if (!confirm("Delete this perspective?")) return;
  await fetch(`http://localhost:8080/api/perspectives/${id}`, { method: "DELETE" });
  loadPerspectives();
}

/* -------------------------------------------------------------------------- */
/* 🟥 RESEARCH REPORTS */
/* -------------------------------------------------------------------------- */
async function loadResearch() {
  const tableBody = document.querySelector("#researchTable tbody");
  try {
    const res = await fetch("http://localhost:8080/api/research");
    const data = await res.json();
    tableBody.innerHTML = "";
    data.forEach(r => {
      tableBody.innerHTML += `
        <tr>
          <td>${r.title || "-"}</td>
          <td>${r.topic || "-"}</td>
          <td>${r.industry || "-"}</td>
          <td>${r.date || "-"}</td>
          <td><span class="status">Published</span></td>
          <td>
            <button onclick="editResearch(${r.id})">✏ Edit</button>
            <button type="button" onclick="deleteResearch(${r.id})">🗑 Delete</button>
          </td>
        </tr>`;
    });
  } catch (err) {
    console.error("Error loading research:", err);
  }
}

function editResearch(id) {
  window.location.href = `form.html?type=research&id=${id}`;
}

async function deleteResearch(id) {
  if (!confirm("Delete this research?")) return;
  await fetch(`http://localhost:8080/api/research/${id}`, { method: "DELETE" });
  loadResearch();
}

async function updateResearch(id, formData) {
  const res = await fetch(`http://localhost:8080/api/research/${id}`, {
    method: "PUT",
    body: formData
  });
  return await res.text();
}

/* -------------------------------------------------------------------------- */
/* 📰 NEWS ARTICLES */
/* -------------------------------------------------------------------------- */
async function loadNewsArticles() {
  const tableBody = document.querySelector("#insightsTable tbody");
  if (!tableBody) return;

  try {
    const res = await fetch("http://localhost:8080/api/news");
    if (!res.ok) throw new Error("Failed to fetch news articles");

    const data = await res.json();
    tableBody.innerHTML = "";

    if (!data.length) {
      tableBody.innerHTML = `<tr><td colspan="7">No news articles available.</td></tr>`;
      return;
    }

    data.forEach((item) => {
      tableBody.innerHTML += `
        <tr>
          <td>${item.articleTitle || "-"}</td>
          <td>${item.topic || "-"}</td>
          <td>${item.industry || "-"}</td>
          <td>News Article</td>
          <td>${item.date || "-"}</td>
          <td><span class="status">Published</span></td>
          <td>
            <button onclick="editNewsArticle(${item.id})">✏ Edit</button>
            <button onclick="deleteNewsArticle(${item.id})">🗑 Delete</button>
          </td>
        </tr>`;
    });
  } catch (err) {
    console.error("Error loading news articles:", err);
  }
}

function editNewsArticle(id) {
  window.location.href = `form.html?type=news-article&id=${id}`;
}

async function deleteNewsArticle(id) {
  if (!confirm("Delete this news article?")) return;
  await fetch(`http://localhost:8080/api/news/${id}`, { method: "DELETE" });
  loadNewsArticles();
}

/* -------------------------------------------------------------------------- */
/* 🟪 PODCASTS */
/* -------------------------------------------------------------------------- */
//document.addEventListener("DOMContentLoaded", loadPodcasts);
async function loadPodcasts() {
  const tableBody = document.querySelector("#insightsTable tbody");
  if (!tableBody) return;

  try {
    const res = await fetch("http://localhost:8080/api/podcasts");
    if (!res.ok) throw new Error("Failed to fetch podcasts");

    const data = await res.json();
    tableBody.innerHTML = "";

    if (!data.length) {
      tableBody.innerHTML = `<tr><td colspan="7">No podcasts available.</td></tr>`;
      return;
    }

    data.forEach((item) => {
      tableBody.innerHTML += `
        <tr>
          <td>${item.title || "-"}</td>
          <td>${item.topic || "-"}</td>
          <td>${item.industry || "-"}</td>
          <td>Podcast</td>
          <td>${item.date || "-"}</td>
          <td><span class="status">Published</span></td>
          <td>
            <button onclick="editPodcast(${item.id})">✏ Edit</button>
            <button onclick="deletePodcast(${item.id})">🗑 Delete</button>
          </td>
        </tr>`;
    });
  } catch (err) {
    console.error("Error loading podcasts:", err);
  }
}

function editPodcast(id) {
  window.location.href = `form.html?type=podcast&id=${id}`;
}

async function deletePodcast(id) {
  if (!confirm("Delete this podcast?")) return;
  await fetch(`http://localhost:8080/api/podcasts/${id}`, { method: "DELETE" });
  loadPodcasts();
}
