
// async function generateCaseStudy() {
//   const form = document.getElementById("caseForm");

//   const caseStudy = {
//     title: document.getElementById("titleInput").value || null,
//     industry: document.getElementById("industryInput").value || null,
//     category: document.getElementById("categoryInput").value || null,
//     overview: document.getElementById("overviewInput").value || null,
//     challenge: document.getElementById("challengeInput").value || null,
//     challengePoints: collectList("#challengePointsContainer") || null,
//     constraints: collectList("#constraintsContainer") || null,
//     approachSteps: collectList("#approachStepsContainer") || null,
//     outcome: document.getElementById("outcomeInput").value || null,
//     madeWork: document.getElementById("madeWorkInput").value || null,
//     createdAt: new Date().toISOString().split("T")[0]
//   };

//   console.log("📤 Sending Case Study:", caseStudy); // Debug log

//   try {
//     const res = await fetch("http://localhost:8080/api/case-studies", {
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//       body: JSON.stringify(caseStudy)
//     });

//     if (!res.ok) {
//       throw new Error("Failed to save case study");
//     }

//     alert("✅ Case Study saved successfully!");
//     form.reset();
//     loadCaseStudies();
//   } catch (err) {
//     console.error("❌ Error saving case study:", err);
//     alert("Error saving case study");
//   }
// }


async function generateCaseStudy() {
  const form = document.getElementById("caseForm");
  const formData = new FormData(form);

  try {
    const response = await fetch("http://localhost:8080/api/casestudies", {
      method: "POST",
      body: formData
    });

    if (!response.ok) throw new Error("Server error");

    const result = await response.json();
    alert("Case Study saved successfully!");
    console.log("Saved:", result);

    loadCaseStudies(); // reload table after save

  } catch (err) {
    console.error("Error saving case study:", err);
    alert("Failed to save case study!");
  }
}


// ======================
// Global state
// ======================
let editingId = null; // Track which case study is being edited

// ======================
// Collect List Helper
// ======================
function collectList(selector) {
  const container = document.querySelector(selector);
  if (!container) return "";
  const inputs = container.querySelectorAll("input, textarea");
  const values = [];
  inputs.forEach((el) => {
    if (el.value.trim()) values.push(el.value.trim());
  });
  return values.join(" | ");
}

// ======================
// Save (Create or Update)
// ======================
async function generateCaseStudy() {
  const form = document.getElementById("caseForm");
  const formData = new FormData(form);

  const method = editingId ? "PUT" : "POST";
  const url = editingId
    ? `http://localhost:8080/api/casestudies/${editingId}`
    : "http://localhost:8080/api/casestudies";

  try {
    const response = await fetch(url, {
      method,
      body: formData
    });

    if (!response.ok) throw new Error("Server error");

    const result = await response.json();
    alert(editingId ? "✅ Case Study updated successfully!" : "✅ Case Study saved successfully!");
    console.log("💾 Saved:", result);

    // Reset form and button
    editingId = null;
    document.getElementById("saveBtn").textContent = "Save Case Study";
    form.reset();

    loadCaseStudies();

  } catch (err) {
    console.error("❌ Error saving case study:", err);
    alert("Failed to save or update case study!");
  }
}

// ======================
// Load Case Studies (List)
// ======================
async function loadCaseStudies() {
  const tableBody = document.querySelector("#caseTable tbody");
  if (!tableBody) return;

  tableBody.innerHTML = `<tr><td colspan="8" style="text-align:center;">Loading...</td></tr>`;

  try {
    const res = await fetch("http://localhost:8080/api/casestudies");
    if (!res.ok) throw new Error("Failed to load case studies");

    const data = await res.json();
    console.log("📥 Loaded Case Studies:", data);

    tableBody.innerHTML = "";

    if (!data.length) {
      tableBody.innerHTML = `<tr><td colspan="8" style="text-align:center;">No case studies found.</td></tr>`;
      return;
    }

    data.forEach((item) => {
      tableBody.innerHTML += `
        <tr>
          <td>${item.id ?? "-"}</td>
          <td>${item.title ?? "-"}</td>
          <td>${item.category ?? "-"}</td>
          <td>${item.industry ?? "-"}</td>
          <td>${item.approachSteps ?? "-"}</td>
          <td>${item.madeWork ?? "-"}</td>
          <td>${item.createdAt ?? "-"}</td>
          <td>
            <button onclick="editCaseStudy(${item.id})">✏️ Edit</button>
            <button onclick="deleteCaseStudy(${item.id})">🗑 Delete</button>
          </td>
        </tr>`;
    });
  } catch (err) {
    console.error("❌ Error loading case studies:", err);
  }
}

// ======================
// Edit Case Study
// ======================
async function editCaseStudy(id) {
  try {
    const res = await fetch(`http://localhost:8080/api/casestudies/${id}`);
    if (!res.ok) throw new Error("Failed to load case study");
    const data = await res.json();

    // Fill form fields
    document.getElementById("title").value = data.title || "";
    document.getElementById("industry").value = data.industry || "";
    document.getElementById("category").value = data.category || "";
    document.getElementById("overview").value = data.overview || "";
    document.getElementById("challenge").value = data.challenge || "";
    document.getElementById("outcome").value = data.outcome || "";
    document.getElementById("madeWork").value = data.madeWork || "";
    document.getElementById("challengePoints").value = data.challengePoints || "";
    document.getElementById("constraints").value = data.constraints || "";
    document.getElementById("approachSteps").value = data.approachSteps || "";

    // Track editing mode
    editingId = id;
    document.getElementById("saveBtn").textContent = "Update Case Study";

    alert("Editing Case Study: " + data.title);
  } catch (err) {
    console.error("❌ Error editing case study:", err);
  }
}

// ======================
// Delete Case Study
// ======================
async function deleteCaseStudy(id) {
  if (!confirm("Are you sure you want to delete this case study?")) return;

  try {
    const res = await fetch(`http://localhost:8080/api/casestudies/${id}`, {
      method: "DELETE"
    });

    if (!res.ok) throw new Error("Failed to delete case study");

    alert("🗑 Case Study deleted successfully!");
    loadCaseStudies();
  } catch (err) {
    console.error("❌ Error deleting case study:", err);
    alert("Failed to delete case study!");
  }
}

// ======================
// On Page Load
// ======================
document.addEventListener("DOMContentLoaded", loadCaseStudies);
