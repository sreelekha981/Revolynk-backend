// package com.example.demo.service;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.demo.entity.AccordionSection;
// import com.example.demo.entity.ContentBlock;
// import com.example.demo.entity.KeyFinding;
// import com.example.demo.entity.ResAuthor;
// import com.example.demo.entity.Research;
// import com.example.demo.repository.ResAuthorRepository;
// import com.example.demo.repository.ResearchRepository;

// @Service
// public class ResearchService {

//     @Autowired
//     private ResearchRepository researchRepository;

//     @Autowired
//     private ResAuthorRepository authorRepository;

   

//     // ✅ Save Research with relations
//     public Research saveResearch(String title, String topic, String industry, String dateStr,
//                                  List<String> authorNames, List<String> authorLinkedIns,
//                                  List<String> findings, List<String> blockTitles, List<String> blockContents,
//                                  List<String> accTitles, List<String> accContents) {

//         Research research = new Research();
//         research.setTitle(title);
//         research.setTopic(topic);
//         research.setIndustry(industry);
//       research.setDate(LocalDate.now());
//  // ensure "yyyy-MM-dd" format

//         // ===== Authors =====
//         List<ResAuthor> authors = new ArrayList<>();
//         for (int i = 0; i < authorNames.size(); i++) {
//             ResAuthor author = new ResAuthor();
//             author.setName(authorNames.get(i));
//             author.setLinkedInUrl(i < authorLinkedIns.size() ? authorLinkedIns.get(i) : "");
//             author.setResearch(research);
//             authors.add(author);
//         }
//         research.setAuthors(authors);

//         // ===== Key Findings =====
//         List<KeyFinding> keyFindingList = new ArrayList<>();
//         for (String f : findings) {
//             KeyFinding kf = new KeyFinding();
//             kf.setFinding(f);
//             kf.setResearch(research);
//             keyFindingList.add(kf);
//         }
//         research.setKeyFindings(keyFindingList);

//         // ===== Content Blocks =====
//         List<ContentBlock> blocks = new ArrayList<>();
//         for (int i = 0; i < blockTitles.size(); i++) {
//             ContentBlock block = new ContentBlock();
//             block.setTitle(blockTitles.get(i));
//             block.setContent(i < blockContents.size() ? blockContents.get(i) : "");
//             block.setResearch(research);
//             blocks.add(block);
//         }
//         research.setContentBlocks(blocks);

//         // ===== Accordion Sections =====
//         List<AccordionSection> sections = new ArrayList<>();
//         for (int i = 0; i < accTitles.size(); i++) {
//             AccordionSection acc = new AccordionSection();
//             acc.setTitle(accTitles.get(i));
//             acc.setContent(i < accContents.size() ? accContents.get(i) : "");
//             acc.setResearch(research);
//             sections.add(acc);
//         }
//         research.setAccordionSections(sections);

//         // Save everything (cascade)
//         return researchRepository.save(research);
//     }

//     // ✅ Get all research
//     public List<Research> getAllResearch() {
//         return researchRepository.findAll();
//     }
// }
package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Research;
import com.example.demo.repository.ResearchRepository;

@Service
public class ResearchService {

    @Autowired
    private ResearchRepository researchRepository;

    public Research saveResearch(
            String title, String topic, String industry, String date,
            List<String> authorNames, List<String> authorDesignations, List<String> authorLinkedIns,
            List<String> keyFindings, List<String> sectionTitles, List<String> sectionContents,
            List<String> accordionTitles
    ) {
        Research research = new Research();
        research.setTitle(title);
        research.setTopic(topic);
        research.setIndustry(industry);
        research.setDate(LocalDate.now());
        research.setAbstractText("Abstract: " + title);
        return researchRepository.save(research);
    }

    public List<Research> getAllResearch() {
        return researchRepository.findAll();
    }

    public Research getResearchById(Long id) {
        return researchRepository.findById(id).orElse(null);
    }

    public Research updateResearch(
            Long id, String title, String topic, String industry, String date,
            List<String> authorNames, List<String> authorDesignations, List<String> authorLinkedIns,
            List<String> keyFindings, List<String> sectionTitles, List<String> sectionContents,
            List<String> accordionTitles
    ) {
        Research existing = getResearchById(id);
        if (existing == null) throw new RuntimeException("Research not found with id: " + id);

        existing.setTitle(title);
        existing.setTopic(topic);
        existing.setIndustry(industry);
        existing.setDate(LocalDate.now());

        return researchRepository.save(existing);
    }

    public void deleteResearch(Long id) {
        researchRepository.deleteById(id);
    }
}
