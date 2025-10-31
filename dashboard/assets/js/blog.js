
async function loadBlogs() {
  const res = await fetch("http://localhost:8080/api/blogs/add");
  const blogs = await res.json();

  const container = document.getElementById("blogList");
  container.innerHTML = blogs.map(b => `
    <div class="blog-card" style="border:1px solid #ccc; padding:10px; margin:10px;">
      <h3>${b.title}</h3>
      <p><b>Topic:</b> ${b.topic}</p>
      <p><b>Industry:</b> ${b.industry}</p>
      <p><b>Date:</b> ${b.date}</p>
      <button onclick="editBlog(${b.id})">✏️ Edit</button>
      <button onclick="deleteBlog(${b.id})">🗑️ Delete</button>
    </div>
  `).join("");
}

async function deleteBlog(id) {
  if (confirm("Are you sure you want to delete this blog?")) {
    const res = await fetch(`http://localhost:8080/api/blogs/add/delete/${id}`, {
      method: "DELETE"
    });
    const msg = await res.text();
    alert(msg);
    loadBlogs(); // refresh list
  }
}

function editBlog(id) {
  window.location.href = `form.html?id=${id}`; // open form for edit
}

loadBlogs();

