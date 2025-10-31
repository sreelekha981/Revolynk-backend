// function renderTable() {
//   const tbody = document.querySelector("#insightsTable tbody");
//   tbody.innerHTML = "";

//   const start = (currentPage - 1) * entries;
//   const end = start + entries;
//   const pageData = insights.slice(start, end);

//   if (pageData.length === 0) {
//     tbody.innerHTML = '<tr><td colspan="7" style="text-align:center;">No insights available.</td></tr>';
//   } else {
//     pageData.forEach((item, i) => {
//       const tr = document.createElement("tr");
//       tr.innerHTML = `
//         <td>${item.title}</td>
//         <td>${item.topic}</td>
//         <td>${item.industry}</td>
//         <td>${item.type}</td>
//         <td>${item.date}</td>
//         <td>${item.status}</td>
//         <td>
//           <button type="button" onclick="editInsight(${i})">✏ Edit</button>
//           <button type="button" onclick="deleteInsight(${i})">🗑 Delete</button>
//         </td>`;
//       tbody.appendChild(tr);
//     });
//   }

//   document.getElementById("pageInfo").innerText =
//     `${currentPage} / ${Math.max(1, Math.ceil(insights.length / entries))}`;
// }

// function editInsight(index) {
//   const item = insights[index];
//   let typeParam = item.type.toLowerCase().replace(" ", "-");
//   window.location.href = `form.html?type=${typeParam}&id=${item.id}`;
// }

// async function deleteInsight(index) {
//   const item = insights[index];
//   if (!confirm(`Delete this ${item.type}?`)) return;

//   const typeUrls = {
//     "Blog": "blogs",
//     "Live Interview": "live-interviews",
//     "Perspective": "perspectives",
//     "Research": "research",
//     "Podcast": "podcasts"
//   };

//   const endpoint = typeUrls[item.type];
//   const res = await fetch(`http://localhost:8080/api/${endpoint}/${item.id}`, {
//     method: "DELETE"
//   });

//   if (res.ok) {
//     alert(`${item.type} deleted successfully`);
//     loadAllInsights();
//   } else {
//     alert(`Failed to delete ${item.type}`);
//   }
// }
