async function loadBlogs() {
  try {
    const res = await fetch("http://localhost:8080/api/blogs");
    if (!res.ok) throw new Error("Failed to fetch blogs");

    const blogs = await res.json();
    const container = document.getElementById("caseGrid");
    container.innerHTML = "";

    blogs.forEach((b) => {
      const imageUrl = b.imagePath
        ? `http://localhost:8080/${b.imagePath}`
        : null;

      const card = document.createElement("div");
      card.className = "case-card";
      card.innerHTML = `
        <div class="rad-card">
          ${imageUrl
            ? `<img src="${imageUrl}" alt="${b.title}" class="rad-card__img">`
            : `<div class="no-image">No image</div>`}
          <div class="rad-card__content">
            <h3>${b.title}</h3>
            <p>${b.introParagraph ? b.introParagraph.substring(0, 120) + "..." : ""}</p>
            <a href="dashboard/blog.html?id=${b.id}" class="rad-card__link">Read More →</a>
          </div>
        </div>
      `;
      container.appendChild(card);
    });
  } catch (err) {
    console.error("Error loading blogs:", err);
  }
}

document.addEventListener("DOMContentLoaded", loadBlogs);





// ..............CaseStudy...............



// async function loadCaseStudies() {
//   try {
//     const res = await fetch("http://localhost:8080/api/caseStudies");
//     if (!res.ok) throw new Error("Failed to fetch CaseStudy");

//     const caseStudies = await res.json();
//     const container = document.getElementById("caseGrid");
//     container.innerHTML = "";

//     caseStudies.forEach(cs => {
//       const imageUrl = cs.imageFile?.startsWith("uploads/")
//         ? `http://localhost:8080/${cs.imageFile}`
//         : `http://localhost:8080/uploads/${cs.imageFile}`;

//       const card = document.createElement("div");
//       card.className = "case-card";
//       card.innerHTML = `
//         <div class="rad-card">
//           <img src="${imageUrl}" alt="${cs.title}" class="rad-card__img">

//           <div class="rad-card__content">
//             <h3 class="rad-card__title">${cs.title}</h3>

//             <div class="rad-card__meta">
//               <span class="rad-topic">${cs.topic}</span> • 
//               <span class="rad-industry">${cs.industry}</span>
//             </div>

//             <div class="rad-card__body">
//               <div class="rad-card__desc">
//                 ${cs.introParagraph ? cs.introParagraph.substring(0, 120) + '...' : ''}
//               </div>
//               <a href="dashboard/blog.html?id=${cs.id}" class="rad-card__link">Read More →</a>
//             </div>
//           </div>
//         </div>
//       `;
//       container.appendChild(card);
//     });

//   } catch (err) {
//     console.error("Error loading case studies:", err);
//     document.getElementById("caseGrid").innerHTML = "<p>Failed to load case studies.</p>";
//   }
// }
// loadCaseStudies();


// // Live Interview




// async function loadCaseStudies() {
//   try {
//     const res = await fetch("http://localhost:8080/api/live_interview");
//     if (!res.ok) throw new Error("Failed to fetch interview");

//     const caseStudies = await res.json();
//     const container = document.getElementById("caseGrid");
//     container.innerHTML = "";

//     caseStudies.forEach(cs => {
//       const imageUrl = cs.imageFile?.startsWith("uploads/")
//         ? `http://localhost:8080/${cs.imageFile}`
//         : `http://localhost:8080/uploads/${cs.imageFile}`;

//       const card = document.createElement("div");
//       card.className = "case-card";
//       card.innerHTML = `
//         <div class="rad-card">
//           <img src="${imageUrl}" alt="${cs.title}" class="rad-card__img">

//           <div class="rad-card__content">
//             <h3 class="rad-card__title">${cs.title}</h3>

//             <div class="rad-card__meta">
//               <span class="rad-topic">${cs.topic}</span> • 
//               <span class="rad-industry">${cs.industry}</span>
//             </div>

//             <div class="rad-card__body">
//               <div class="rad-card__desc">
//                 ${cs.introParagraph ? cs.introParagraph.substring(0, 120) + '...' : ''}
//               </div>
//               <a href="dashboard/blog.html?id=${cs.id}" class="rad-card__link">Read More →</a>
//             </div>
//           </div>
//         </div>
//       `;
//       container.appendChild(card);
//     });

//   } catch (err) {
//     console.error("Error loading case studies:", err);
//     document.getElementById("caseGrid").innerHTML = "<p>Failed to load case studies.</p>";
//   }
// }
// loadCaseStudies();




// // NewsArticle




// async function loadCaseStudies() {
//   try {
//     const res = await fetch("http://localhost:8080/api/news");
//     if (!res.ok) throw new Error("Failed to fetch news");

//     const caseStudies = await res.json();
//     const container = document.getElementById("caseGrid");
//     container.innerHTML = "";

//     caseStudies.forEach(cs => {
//       const imageUrl = cs.imageFile?.startsWith("uploads/")
//         ? `http://localhost:8080/${cs.imageFile}`
//         : `http://localhost:8080/uploads/${cs.imageFile}`;

//       const card = document.createElement("div");
//       card.className = "case-card";
//       card.innerHTML = `
//         <div class="rad-card">
//           <img src="${imageUrl}" alt="${cs.title}" class="rad-card__img">

//           <div class="rad-card__content">
//             <h3 class="rad-card__title">${cs.title}</h3>

//             <div class="rad-card__meta">
//               <span class="rad-topic">${cs.topic}</span> • 
//               <span class="rad-industry">${cs.industry}</span>
//             </div>

//             <div class="rad-card__body">
//               <div class="rad-card__desc">
//                 ${cs.introParagraph ? cs.introParagraph.substring(0, 120) + '...' : ''}
//               </div>
//               <a href="dashboard/blog.html?id=${cs.id}" class="rad-card__link">Read More →</a>
//             </div>
//           </div>
//         </div>
//       `;
//       container.appendChild(card);
//     });

//   } catch (err) {
//     console.error("Error loading case studies:", err);
//     document.getElementById("caseGrid").innerHTML = "<p>Failed to load case studies.</p>";
//   }
// }
// loadCaseStudies();




// //Perspective




// async function loadCaseStudies() {
//   try {
//     const res = await fetch("http://localhost:8080/api/perspectives");
//     if (!res.ok) throw new Error("Failed to fetch CaseStudy");

//     const caseStudies = await res.json();
//     const container = document.getElementById("caseGrid");
//     container.innerHTML = "";

//     caseStudies.forEach(cs => {
//       const imageUrl = cs.imageFile?.startsWith("uploads/")
//         ? `http://localhost:8080/${cs.imageFile}`
//         : `http://localhost:8080/uploads/${cs.imageFile}`;

//       const card = document.createElement("div");
//       card.className = "case-card";
//       card.innerHTML = `
//         <div class="rad-card">
//           <img src="${imageUrl}" alt="${cs.title}" class="rad-card__img">

//           <div class="rad-card__content">
//             <h3 class="rad-card__title">${cs.title}</h3>

//             <div class="rad-card__meta">
//               <span class="rad-topic">${cs.topic}</span> • 
//               <span class="rad-industry">${cs.industry}</span>
//             </div>

//             <div class="rad-card__body">
//               <div class="rad-card__desc">
//                 ${cs.introParagraph ? cs.introParagraph.substring(0, 120) + '...' : ''}
//               </div>
//               <a href="dashboard/blog.html?id=${cs.id}" class="rad-card__link">Read More →</a>
//             </div>
//           </div>
//         </div>
//       `;
//       container.appendChild(card);
//     });

//   } catch (err) {
//     console.error("Error loading case studies:", err);
//     document.getElementById("caseGrid").innerHTML = "<p>Failed to load case studies.</p>";
//   }
// }
// loadCaseStudies();







// //Podcasts





// async function loadCaseStudies() {
//   try {
//     const res = await fetch("http://localhost:8080/api/podcats");
//     if (!res.ok) throw new Error("Failed to fetch podcats");

//     const caseStudies = await res.json();
//     const container = document.getElementById("caseGrid");
//     container.innerHTML = "";

//     caseStudies.forEach(cs => {
//       const imageUrl = cs.imageFile?.startsWith("uploads/")
//         ? `http://localhost:8080/${cs.imageFile}`
//         : `http://localhost:8080/uploads/${cs.imageFile}`;

//       const card = document.createElement("div");
//       card.className = "case-card";
//       card.innerHTML = `
//         <div class="rad-card">
//           <img src="${imageUrl}" alt="${cs.title}" class="rad-card__img">

//           <div class="rad-card__content">
//             <h3 class="rad-card__title">${cs.title}</h3>

//             <div class="rad-card__meta">
//               <span class="rad-topic">${cs.topic}</span> • 
//               <span class="rad-industry">${cs.industry}</span>
//             </div>

//             <div class="rad-card__body">
//               <div class="rad-card__desc">
//                 ${cs.introParagraph ? cs.introParagraph.substring(0, 120) + '...' : ''}
//               </div>
//               <a href="dashboard/blog.html?id=${cs.id}" class="rad-card__link">Read More →</a>
//             </div>
//           </div>
//         </div>
//       `;
//       container.appendChild(card);
//     });

//   } catch (err) {
//     console.error("Error loading case studies:", err);
//     document.getElementById("caseGrid").innerHTML = "<p>Failed to load case studies.</p>";
//   }
// }
// loadCaseStudies();






// // Research Report


// async function loadCaseStudies() {
//   try {
//     const res = await fetch("http://localhost:8080/api/research");
//     if (!res.ok) throw new Error("Failed to fetch Research Report");

//     const caseStudies = await res.json();
//     const container = document.getElementById("caseGrid");
//     container.innerHTML = "";

//     caseStudies.forEach(cs => {
//       const imageUrl = cs.imageFile?.startsWith("uploads/")
//         ? `http://localhost:8080/${cs.imageFile}`
//         : `http://localhost:8080/uploads/${cs.imageFile}`;

//       const card = document.createElement("div");
//       card.className = "case-card";
//       card.innerHTML = `
//         <div class="rad-card">
//           <img src="${imageUrl}" alt="${cs.title}" class="rad-card__img">

//           <div class="rad-card__content">
//             <h3 class="rad-card__title">${cs.title}</h3>

//             <div class="rad-card__meta">
//               <span class="rad-topic">${cs.topic}</span> • 
//               <span class="rad-industry">${cs.industry}</span>
//             </div>

//             <div class="rad-card__body">
//               <div class="rad-card__desc">
//                 ${cs.introParagraph ? cs.introParagraph.substring(0, 120) + '...' : ''}
//               </div>
//               <a href="dashboard/blog.html?id=${cs.id}" class="rad-card__link">Read More →</a>
//             </div>
//           </div>
//         </div>
//       `;
//       container.appendChild(card);
//     });

//   } catch (err) {
//     console.error("Error loading case studies:", err);
//     document.getElementById("caseGrid").innerHTML = "<p>Failed to load case studies.</p>";
//   }
// }
// loadCaseStudies();