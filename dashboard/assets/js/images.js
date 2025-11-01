async function loadBlogDetail() {
  const params = new URLSearchParams(window.location.search);
  const id = params.get("id");

  if (!id) {
    document.getElementById("blogDetail").innerHTML = "<p>No blog selected.</p>";
    return;
  }

  try {
    const res = await fetch(`http://localhost:8080/api/blogs/${id}`);
    if (!res.ok) throw new Error("Failed to fetch blog");

    const blog = await res.json();
    console.log(blog); // 👈 Debug: check what backend sends

    const imageUrl = blog.imagePath
      ? `http://localhost:8080/${blog.imagePath.startsWith("uploads/") ? "" : "uploads/"}${blog.imagePath}`
      : "";

    document.getElementById("blogDetail").innerHTML = `
      <div class="content">
        <div class="meta">Blog</div>
        <h1>${blog.title}</h1>

        <div class="info-row">
          <span>${blog.topic || "N/A"}</span>
          <span>${blog.industry || "N/A"}</span>
        </div>

        <div class="date-row">${blog.date || ""}</div>
        <div class="highlight-bar"></div>

        <p>${blog.introParagraph || ""}</p>

        ${imageUrl ? `<div class="image-section"><img src="${imageUrl}" alt="${blog.title}" /></div>` : ""}

        <p>${blog.paragraphAfterImage || ""}</p>

        ${
          blog.sections && blog.sections.length
            ? blog.sections.map(s => `<h2>${s.title || ""}</h2><p>${s.content || ""}</p>`).join("")
            : ""
        }

        <div class="tp-faq-wrap">
          <div class="tp-custom-accordion">
            <div class="accordion" id="general_faqaccordion">
              ${
                blog.accordions && blog.accordions.length
                  ? blog.accordions.map((a, i) => `
                      <div class="accordion-item mb-25">
                        <h2 class="accordion-header" id="acc_${i}">
                          <button class="accordion-button tp-faq-btn collapsed" 
                            type="button" data-bs-toggle="collapse"
                            data-bs-target="#collapse_${i}"
                            aria-expanded="false" aria-controls="collapse_${i}">
                            ${a.title}
                            <span class="accordion-btn"><i class="fa-solid fa-chevron-down"></i></span>
                          </button>
                        </h2>
                        <div id="collapse_${i}" class="accordion-collapse collapse"
                          aria-labelledby="acc_${i}" data-bs-parent="#general_faqaccordion">
                          <div class="accordion-body tp-faq-details-para">
                            <p>${a.content}</p>
                          </div>
                        </div>
                      </div>
                    `).join("")
                  : "<p>No FAQs available.</p>"
              }
            </div>
          </div>
        </div>
      </div>

      ${
        blog.authors && blog.authors.length
          ? `<div class="sidebar"><h3>Written by</h3>
              ${blog.authors.map(a => `
                <div class="author-info">
                  <strong>${a.name}</strong><br>${a.role || ""}
                  ${a.linkedin ? `<a href="${a.linkedin}" target="_blank"><i class="fab fa-linkedin"></i></a>` : ""}
                </div>
              `).join("")}
            </div>`
          : ""
      }

      <br><a href="insights.html">← Back to Blogs</a>
    `;
  } catch (err) {
    console.error("Error loading blog:", err);
    document.getElementById("blogDetail").innerHTML = "<p>Failed to load blog.</p>";
  }
}

document.addEventListener("DOMContentLoaded", loadBlogDetail);
