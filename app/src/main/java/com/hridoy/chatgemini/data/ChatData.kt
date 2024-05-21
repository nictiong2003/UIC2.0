// ChatData.kt

package com.hridoy.chatgemini.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.Content
import com.google.ai.client.generativeai.type.TextPart
import com.hridoy.chatgemini.BuildConfig
import com.hridoy.chatgemini.domain.model.Chat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ChatData {
    private const val api_key = BuildConfig.API_KEY

    suspend fun getResponse(prompt: String): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.0-pro",
            apiKey = api_key,
        )

        return try {

            val content = Content(parts = listOf(
                TextPart("input: Hi, how are you?"),
                TextPart("output: Hi, I am find. How can I help you?"),
                TextPart("input: Good morning"),
                TextPart("output: Good morning, how can I help you?"),
                TextPart("input: Good afternoon"),
                TextPart("output: Good afternoon, how can I help you?"),
                TextPart("input: Good evening"),
                TextPart("output: Good evening, how can I help you?"),
                TextPart("input: who are you?"),
                TextPart("output: I am an UIC chatbot. My mission is to let you know more about UTS."),
                TextPart("input: What is UTS? Introduce UTS."),
                TextPart("output: University of Technology Sarawak (UTS) is established to support Sarawak's vision for the Sarawak Corridor of Renewable Energy (SCORE). It offers affordable, high-quality education in science and technology, emphasizing applied learning for career opportunities in SCORE-related industries. It boasts modern facilities and will have its Science and Technology Park for research and collaboration. Partnering with Universiti Kuala Lumpur, UTS aims to meet the human resource needs of SCORE. It offers Transfer Degree Programmes with universities from Australia, the UK, and New Zealand."),
                TextPart("input: What is the vision of UTS?"),
                TextPart("output: Our vision is to be a global technical university founded on science and technology."),
                TextPart("input: What is the mission of UTS?"),
                TextPart("output: Our mission is to provide world-class educational programs and learning environment to nurture creative and entrepreneurial leaders through a broad yet specialised education in science and technology."),
                TextPart("input: What is the core of UTS?"),
                TextPart("output: The core values that guide us in our mission are Commitment, Integrity, Caring, Enterprising, and Green."),
                TextPart("input: What is the Website/Webpage of UTS?"),
                TextPart("output: https://www.uts.edu.my"),
                TextPart("input: Where is the location of UTS?"),
                TextPart("output: No. 1 Jalan Universiti, 96000 Sibu, Sarawak, Malaysia."),
                TextPart("input: Are the programs in UTS recognized by UTS?"),
                TextPart("output: University of Technology Sarawak (UTS) is pleased to announce that all its programs are recognized, approved, and accredited by the Ministry of Higher Education (MOHE) and the Malaysian Qualifications Agency (MQA)."),
                TextPart("input: Can you provide more details about MQA?"),
                TextPart("output: To view UTS's accreditation listings, search for \"University of Technology Sarawak\" on the respective MQA webpages."),
                TextPart("input: Could you describe the facilities available on the UTS campus?"),
                TextPart("output: The UTS campus is equipped with modern facilities, including three lecture theatres with varying seating capacities, located within the Built Environment Centre and the Management Science and General Studies Centre."),
                TextPart("input: When is the intake for Foundation?"),
                TextPart("output: There are 2 intake, June and November."),
                TextPart("input: When is the intake for Undergraduate?"),
                TextPart("output: September and February."),
                TextPart("input: When is the intake for Postgraduate?"),
                TextPart("output: There are 2 types, by coursework or by research:\n1. Coursework: September Intake, February Intake and July Intake\n2. Research: September Intake and December Intake"),
                TextPart("input: What is the entry requirements for Foundation in Arts, FIA?"),
                TextPart("output: Entry Requirement for FIA is to pass SPM/O-level equivalent with 5 Grade C's or pass UCE with 4 Grade B's."),
                TextPart("input: What is the entry requirements for Foundation in Science, FIS?"),
                TextPart("output: The entry requirement for FIS is to pass SPM/O-level equivalent with 5 Grade C's including Mathematics and 1 Science subject(Physics or Chemistry) or pass UCE with 3 Grade B's including Mathematics and 1 Science subject (Physics or Chemistry)."),
                TextPart("input: How many foundation programs in UTS?"),
                TextPart("output: In Foundation, there are 2 programmes: Foundation in Arts and Foundation in Science."),
                TextPart("input: How many undergraduate programs in UTS?"),
                TextPart("output: In Undergraduate, there are 17 programmes."),
                TextPart("input: How many postgraduate programs in UTS?"),
                TextPart("output: In Postgraduate, there are 10 programmes"),
                TextPart("input: What are the postgraduate programs in UTS?"),
                TextPart("output: 1. Master in Computing\n2. Master of Architecture\n3. Master of Engineering\n4. Master of Project Management\n5. Master of Science in Applied Sciences\n6. Master of Science in Business Management\n7. Master of Science in Construction Management\n8. Master of Science in Architecture\n9. Master of Construction Management\n10. Master of Business Administration"),
                TextPart("input: What are the undergraduate programs in UTS?"),
                TextPart("output: 1. Bachelor of Food Technology (Hons)\n2. Bachelor of Civil Engineering (Hons)\n3. Bachelor of Mechanical Engineering (Hons)\n4. Bachelor of Electrical Engineering (Hons) \n5. Bachelor of Science in Occupational Safety and Health (Hons) 6. Bachelor of Business Administration (Hons)\n7. Bachelor of Accountancy (Hons)\n8. Bachelor of Business (Hons) in Marketing\n9. Bachelor of Technology Management (Hons)\n10. Bachelor of Science in Architecture (Hons)\n11. Bachelor of Quantity Surveying (Hons)\n12. Bachelor of Art (Hons) Interior Design\n13. Bachelor of Science in Property and Construction Management (Hons)\n14. Bachelor of Computer Science (Hons)\n15. Bachelor of Arts in Industrial Design (Hons)\n16.Bachelor of Arts (Hons) in Creative Digital Media\n17. Bachelor of Mobile Game Development (Hons)"),
                TextPart("input: How many PhD programs in UTS?"),
                TextPart("output: In Doctor (PhD),there are 6 programmes."),
                TextPart("input: What are the PhD programs in UTS?"),
                TextPart("output: 1. Doctor of Philosophy (PhD) in Computing\n2. Doctor of Philosophy (PhD) in Engineering\n3. Doctor of Philosophy (PhD) in Applied Science\n4. Doctor of Philosophy (PhD) in Business Management\n5. Doctor of Philosophy (PhD) in Architecture\n6. Doctor of Philosophy (PhD) in Construction Management."),
                TextPart("input: What is the entry requirements for Bachelor of Mechanical Engineering?"),
                TextPart("output: The entry requirements are to pass STPM/A-level with full pass in Mathematics and Physical Science; or Pass UEC with five (5) Grade B’s including Mathematics and Physical Science; or Pass Matriculation /Pre-U/Foundation in Science from recognised institutions with minimum CGPA 2.0; or Pass Diploma in related field from recognised institutions with minimum CGPA 2.0; or Pass a DKM/DKLM/DVM with a minimum CGPA of 2.50; or Pass DKM/ DKLM / DVM with at least a CGPA of 2.00 with at least two (2) years of work experience in related field; or Other equivalent qualifications recognised by the Malaysian Government and approved by University Senate."),
                TextPart("input: What is the entry requirements for Bachelor of Civil Engineering?"),
                TextPart("output: The entry requirements are to pass STPM/A-level with a full pass in Mathematics and Physical Science; orPass UEC with five (5) Grade B’s including Mathematics and Physical Science; orPass Matriculation / Pre-U/ Foundation in Science from recognised institutions with minimum CGPA 2.00; orPass Diploma in related field from recognised institutions with minimum CGPA 2.00; orPass a DKM /DLKM ?DVM with a minimum CGPAof 2.50; orPass DKM / DKLM / DVM with at least a CGPA of 2.00 with at least two (2) years of work experience in related field;orOther equivalent qualifications recognised by the Malaysian Government and approved by University Senate."),
                TextPart("input: What is the entry requirements for Bachelor of Electrical Engineering?"),
                TextPart("output: The entry requirements is to pass STPM/A-level with full pass in Mathematics and Physical Science; orPass UEC with five (5) Grade B’s including Mathematics and Physical Science; orPass Matriculation / Pre-U/ Foundation in Science from recognised institutions with minimum CGPA 2.00; orPass Diploma in related field from recognised institutions with minimum CGPA 2.00; orPass a DKM / DLKM / DVM with a minimum CGPA of 2.50; orPass DKM / DLKM / DVM with at least a CGPA of 2.00 with at least two (2) years of work experience in related field; or Other equivalent qualifications recognised by the Malaysian Government and approved by University Senate.The candidate is not colour blind as not to hinder from doing practical work."),
                TextPart("input: What is the entry requirements for Bachelor of Food Technology?"),
                TextPart("output: Pass STPM or its equivalent with at least Grade C (NGMP 2.00) in Two (2) subjects; 0rPass UEC with five (5) Grade B’s; orPass Matriculation / Foundation in any IPTA/ IPTS/ permitted institutions to conduct foundation programmes with CGPA 2.00; orPass Diploma in related disciplines with CGPA 2.00; orPass Advanced Diploma in related disciplines with CGPA 2.00; orPass UEC with at least Grade B in Five (5) subjects; orPass STAM (Grade Jayyid) or its equivalent; orOther recognised qualifications or its equivalent."),
                TextPart("input: What is the entry requirements for Bachelor of Science in Occupational Safety and Health?"),
                TextPart("output: A pass in STPM with a minimum of Grade C (GPA 2.00) in any two (2) subjects, and a pass in Science at the SPM level or its equivalent; ORA pass in STAM with at least Grade Jayyid*; OR Matriculation or Foundation with at least CGPA of 2.00*; ORA Diploma (Level 4, MQF) with at least CGPA of 2.00*; OR An Advanced Diploma (Level 5, MQF) with at least CGPA of 2.00; OR Pass UEC with five (5) Grade B’s including Mathematics or Physical Science; ORA Diploma Kemahiran Malaysia (DKM) /Diploma Vokasional Malaysia (DVM);      subjected to HEP Senate / Academic Board’s approval; ORA Diploma Lanjutan Kemahiran Malaysia (DLKM) subjected to HEP Senate /Academic    Board’s approval; OROther equivalent qualifications recognised by the Malaysian GovernmentInternational student is required to achieve a minimum Band 3 in MUET OR equivalent to Common European Framework of Reference for Languages (Low B2) **Note for (vi) & (vii): The HEPs are to conduct screening and provide necessary guidance specific to the discipline of the programme\n*A pass in English at Sijil Pelajaran Malaysia level or equivalent qualifications, a credit in Mathematics or ONE Science subject at SPM level or equivalent qualifications\n**Refer to Surat JPT GS 1000-630(41), 9th December 2019 – Syarat Kompetensi Bahasa Inggeris Kepada Pelajar"),
                TextPart("input: What is the entry requirements for Bachelor of Business Administration?"),
                TextPart("output: Pass STPM/ A-level with full pass in two (2) subjects or CGPA 2.0; or Pass UEC with five (5) Grade B’s; or Pass Matriculation/Pre-U/ Foundation from recognised institutions with minimum CGPA 2.0; or Pass Diploma in related field from recognised institutions with minimum CGPA 2.0; or Diploma Vocational Malaysia (DVM) equivalent to credits in any 3 subjects SPM level; and pass with credit in Bahasa Melayu 1104; and minimum academic CGPA of 2.00 out of 4.00; and minimum vocational CGPA of 2.67 out of 4.00; and competence in all vocational modules and for SVM cohort 2012-2016 pass in History; or Other equivalent qualifications recognitions recognised by the Malaysian Government.Pass Diploma Kemahiran Malaysia (DKM) / Diploma Lanjutan Kemahiran Malaysia (DLKM) / Diploma Vokasional Malaysia (DVM) with a minimum CGPA of 2.5 subject to UTS Senate approval; or Pass DKM / DLKM / DVM with a minimum CGPA of 2.00 AND have at least 2 years of work experience in a related field."),
                TextPart("input: What is the entry requirements for Bachelor of Accountancy?"),
                TextPart("output: Pass STPM or equivalent with minimum Grade C+ (CGPA 2.33) in any two (2) subjects and credit in Mathematics and a pass in English at SPM level or equivalent; or Pass GCE A-level with a minimum of one (1) Grade  B and two (2) Grade C’s (including Mathematics); or Pass UEC with five (5) Grade B’s including Mathematics and a pass in English; or Pass STAM with Jayyid, and credit in Mathematics and a pass in English at SPM level; or Pass Matriculation / Pre-U / Foundation from recognised institutions with a minimum CGPA of 2.50 and credit in Mathematics and a pass in English at SPM level or equivalent;  or Pass a Diploma in Accounting or a related field from recognised institutions with a minimum CGPA of 2.50, and credit in Mathematics and a pass in English at SPM level or equivalent; Obtained at least Band 2 at the Malaysian University English Test (MUET); or Other equivalent qualifications recognised by the Malaysian GovernmentPass Diploma Kemahiran Malaysia (DKM) / Diploma Lanjutan Kemahiran Malaysia (DLKM) / Diploma Vokasional Malaysia (DVM) with a minimum CGPA of 2.50 subject to UTS Senate approval; or Pass DKM / DLKM / DVM with a minimum CGPA of  2.00 AND have at least 2 years of work experience in a related field, and MUET Band 2 is required as a condition for graduation"),
                TextPart("input: What is the entry requirements for Bachelor of Business in Marketing?"),
                TextPart("output: Pass STPM with minimum Grade C (GP 2.00) in any two (2) subjects, and a pass in Mathematics and English at SPM level or equivalent; or Pass STAM with a minimum Grade of Jayyid and a pass in Mathematics and English at SPM level; or Any qualifications equivalent to Diploma or Advanced Diploma (MQF, Level 4 or 5); or Matriculation/Foundation qualification with a minimum CGPA of 2.00 out of 4.00; or Pass in UEC with five (5) Grade B’s; or Other equivalent qualifications recognized by the Malaysian GovernmentNote: International students are required to achieve a minimum score of 5.5 for IELTS or its equivalent"),
                TextPart("input: What is the entry requirements for Bachelor of Technology Management?"),
                TextPart("output: A pass in STPM, with minimum Grade C (GP 2.00) in any 2 subjects, and a pass in Mathematics and English at SPM level or any equivalent qualification; ORA pass in STAM with a minimum Grade of Jayyid and a pass in Mathematics and English at SPM level; OR Any qualifications equivalent to Diploma or Advanced Diploma (MQF, Level 4 or 5); OR Matriculation/Foundation qualification with a minimum CGPA of 2.00 out of 4.00; ORA pass in Unified Examination Certificate (UEC) with a minimum Grade B in five subjects; OR Diploma Vocational Malaysia (DVM) equivalent to credits in any 3 subjects SPM level; and pass with credit in Bahasa Melayu 1104; and minimum academic CGPA of 2.00 out of 4.00; and minimum vocational CGPA of 2.67 out of 4.00; and competence in all vocational modules and for SVM cohort 2012-2016 pass in History OR Any equivalent qualification."),
                TextPart("input: What is the entry requirements for Bachelor of Quantity Surveying?"),
                TextPart("output: Pass STPM minimum Grade C (2.00) in three (3) subjects; or Pass UEC with minimum Grade B in five (5) subjects including credits in Mathematics, Bahasa Malaysia AND English; or Pass Matriculation Programme with minimum CGPA of 2.50; or Pass Foundation Programme with minimum CGPA of 2.50 offered by HEP recognised by the Government of Malaysia, or equivalent; or Pass A-Levels Programme with minimum Grade D in three (3) subjects including Mathematics; or Students with an accredited Diploma in QS must obtain a minimum CGPA of 2.67 to be eligible for advanced entry into a QS degree programme; orMatured students with relevant qualifications and experience, and students with a diploma in QS or related disciplines may be exempted from certain subjects and may be given advanced entry, the duration of which may be decided based on the previous programme attended, qualifications and grades achieved and / or the number of years of relevant experience held. These students are to be assessed on a case by case basis by the HEP in accordance with the latest BQSM requirements particularly Preambles of the List of Accredited Qualifications and Guidelines of Advanced Entry. Important: Student having SPM qualification must possess a minimum of three (3) credits including Mathematics, Bahasa Malaysia OR English"),
                TextPart("input: What is the entry requirements for Bachelor of Science in Architecture?"),
                TextPart("output: Pass STPM /A-Level with full pass in two (2) subjects and SPM/O-level credit in Mathematics; orPass UEC with five (5) Grade B’s including Mathematics; orPass Matriculation / Pre-U / Foundation from recognised institutions with minimum CGPA 2.0 and Pass SPM / O-level equivalent with a minimum of credit in Mathematics; orPass Diploma in related fields from recognised institutions with minimum CGPA 2.0; orOther equivalent qualifications recognised by the Malaysian Government; andSPM/O-level pass in Visual Arts / Pass UCTS Portfolio Assessment / Interview"),
                TextPart("input: What is the entry requirements for Bachelor of Art in Interior Design?"),
                TextPart("output: Pass in MQA accredited foundation programme (Science or Arts) or equivalent with CGPA 2.0 minimum; or Pass in MQA accredited related Diploma programme or equivalent with CGPA 2.0 minimum; or2 passes STPM/GCE ‘A’ Level or equivalent with minimum 2C; or5 Grade B in UEC including Maths and Science, or Other equivalent qualifications which are recognised by Malaysian Government, and approved by University Senate. And Pendidikan Seni & Portfolio ReviewEnglish language requirements for INTERNATIONAL applicants : Pass in Malaysian Universiry English Test (MUET) with minimum Band 3."),
                TextPart("input: What is the entry requirements for Bachelor of Science in Property and Construction Management?"),
                TextPart("output: Pass Sijil Tertinggi Persekolahan Malaysia (STPM) with at least Grade C (CGPA 2.00) in three (3) subjects or equivalent; or Pass Matriculation programme under the Ministry of Education Malaysia with minimum CGPA of 2.00 or equivalent; or Pass Foundation studies or equivalent; or Possess Diploma or any other equivalent qualification recognised by Ministry of Education Malaysia; or A-Level or equivalent as recognised by Ministry of Education, Malaysia for international students \nNote: Students admitted to a bachelor degree programme based on their Diploma qualification as entry requirement shall be allowed for transfer of credits for relevant subjects/ courses offered in the first year of the programme and subject to a maximum 30% of total credit transfer."),
                TextPart("input: What is the entry requirements for Bachelor of Computer Science?"),
                TextPart("output: A pass in Matriculation or Foundation studies with a minimum CGPA of 2.00; AND a credit in: Additional Mathematics at SPM level or its equivalent; OR Mathematics and any one of the Science, Technology or Engineering subjects at SPM level or its equivalent. Candidates need to take and pass the Reinforcement Mathematics equivalent to Additional Mathematics at the SPM level. The subject must be offered in the first semester or before the enrolment with unconditional offer. \nNote: Students from Matriculation / Foundation or its equivalent can be exempted from taking the Reinforcement Mathematics, provided that the Mathematics offered at that programme level is equivalent / more than the Additional Mathematics offered at an SPM level. OR; A pass in STPM (Art Stream) with a minimum grade of C (CGPA 2.00) in any TWO (2) subjects; AND a credit in: Additional Mathematics at SPM level or its equivalent; OR Mathematics and any one of the Science, Technology or Engineering subjects at SPM level or its equivalent. Candidates need to take and pass the Reinforcement Mathematics equivalent to Additional Mathematics at the SPM level. The subject must be offered in the first semester or before the enrolment with unconditional offer. OR; A pass in STAM with a minimum grade of Jayyid in any TWO (2) subjects; AND a credit in: Additional Mathematics at SPM level or its equivalent; OR Mathematics and any one of the Science, Technology or Engineering subjects at SPM level or its equivalent. Candidates need to take and pass the Reinforcement Mathematics equivalent to Additional Mathematics at the SPM level. The subject must be offered in the first semester or before the enrolment with unconditional offer. OR; Any Diploma in Science and Technology (Level 4, MQF) with a minimum CGPA of 2.75. Candidates with a CGPA below 2.75 but more than 2.50 can be admitted subject to a thorough rigorous assessment; AND a credit in: Additional Mathematics at SPM level or its equivalent; OR Mathematics and any one of the Science, Technology or Engineering subjects at SPM level or its equivalent. Candidates need to take and pass the Reinforcement Mathematics equivalent to Additional Mathematics at the SPM level. The subject must be offered in the first semester or before the enrolment with unconditional offer. OR; Pass UEC with a minimum Grade B in 5 subjects including a Mathematics subject that is equivalent to the Additional Mathematics at SPM level OR; A pass in STPM (Science Stream) or its equivalent with a minimum grade of C (CGPA 2.00) in Mathematics subject and ONE (1) Science / ICT subject OR; Diploma in Computing fields (Level 4, MQF) or its equivalent with a minimum CGPA of 2.50. Candidates with a CGPA below 2.50 but more than 2.0 may be admitted subject to thorough internal evaluation process OR; Diploma Kemahiran Malaysia (DKM) / Diploma Vokasional Malaysia (DVM) in Computing fields with a minimum CGPA of 2.50 subjected to HEP Senate / Academic Board’s approval OR; Diploma Lanjutan Kemahiran Malaysia (DLKM) in Computing fields with a minimum CGPA of 2.50 subjected to HEP Senate / Academic Board’s approval OR; Other relevant and equivalent qualifications recognised by the Malaysia Government. (Candidates can be admitted if their admission qualification contains Mathematics subject(s) equivalent to Additional Mathematics at the SPM level. If it is not equivalent, reinforcement Mathematics subject that equivalent to the SPM level must be offered in the first semester or before enrolment with unconditional offer)."),
                TextPart("input: What is the entry requirements for Bachelor of Arts in Industrial Design?"),
                TextPart("output: Pass STPM or equivalent, with a minimum Subject Grade Value(NGMP) 2.00 and credit in Bahasa Malaysia subject and pass English Language subject at SPM level; OR Pass Unified Examination Certificate (UEC) with minimum Grade B in five (5) subjects; OR Pass Diploma (KKM, Level 4) from recognized institution or equivalent with a minimum CGPA 2.00; OR Pass Matriculation/Foundation from recognized institution or equivalent with minimum CGPA 2.00; OR Pass Diploma Vocational Malaysia (DVM) (from any relevant Art and Design Programmes such as but not limited to: Fine Arts, Graphic Design, Animation, Creative Media, Product Design, Fashion Design, Furniture Design, Interior Design, Automotive Design, Traditional Arts and Crafts Design, any other Art and Design Fields) equivalent to credits in any 3 subjects SPM level; pass with credit in Bahasa Melayu 1104; minimum academic CGPA of 2.00; minimum vocational CGPA of 2.67; competence in all vocational modules; SVM cohort 2012-2016 pass History at SPM level OR Other equivalent qualifications subject to approval by university’s senate and recognized by Malaysian Government AND Pass UCTS Interview and Portfolio Assessment."),
                TextPart("input: What is the entry requirements for Bachelor of Arts in Creative Digital Media?"),
                TextPart("output: Pass STPM or equivalent with at least a Grade C (NGMP 2.0) in 2 subjects; or Pass STAM (Jayyid); or Pass UEC with at least a B grade in 5 subjects; or Pass Matriculation / Foundation / Foundation from any IPTA / IPTS / Institution that is allowed to offer foundation program with CGPA of 2.00; or Creative Multimedia Diploma with a minimum CGPA of 2.5 (candidates with CGPA below 2.50 but above 2.00 may be subject to internal evaluation process); or Other equivalent qualifications subject to approval by university’s senate and recognized by Malaysian Government."),
                TextPart("input: What is the entry requirements for Bachelor of Mobile Game Development?"),
                TextPart("output: A pass in STPM with at least Grade C (GP 2.00) in any TWO (2) subjects; ORA pass in STAM with at least grade Jayyid; OR Matriculation or Foundation with at least CGPA of 2.00; ORA Diploma (Level 4, MQF) with at least CGPA of 2.00; OR An Advanced Diploma (Level 5, MQF) with at least CGPA of 2.00; ORA Diploma Kemahiran Malaysia (DKM) / Diploma Vokasional Malaysia (DVM) subjected to HEP Senate / Academic Board’s approval*; ORA Diploma Lanjutan Kemahiran Malaysia (DLKM) subjected to HEP Senate / Academic Board’s approval*; OR Unified Examination Certificate with a minimum Grade B in 5 subjects; OR Other relevant equivalent qualifications recognised by the Malaysian Government; AND Pass an interview (online / virtual / conventional) OR submission of student’s portfolio, to be determined by the HEP as required. AND For international student, Test of English as a Foreign Language (TOEFL) must pass with score of 500 or International English Language Testing (IELTS) with score of 5.0 or its equivalent. In the event that the student did not meet the criteria, Higher Educational Provider has to offer English course to ensure the student proficiency to meet the needs of programme. This is usually done through evaluation process."),
                TextPart("input: How many clubs UTS have?"),
                TextPart("output: UTS have 33 clubs."),
                TextPart("input: What are the clubs in UTS?"),
                TextPart("output: Religion: \nCampus Fellowship , Mandarin/BMB, Life, Buddhist, Permai, Youth Growth\n\nAcademic: \nUnitech\n\nUniform:\nUTS Red Crescent Society\n\nSports:\nVolleyball, Basketball, Table Tennis, Wushu, Netball, Football & Futsal, Badminton, Frisbee, Pencak Silat\n\nRecreational:\nMusic, MIFA, Modern Dance, Photography & Videography, IEEE, Psquad, JCI Youth UTS, Green, Indoor Game, Esports, Sri Tari Kenyalang, Dayak Youth, Mandarin, Toastmasters, Leo, RISE"),
                TextPart("input: I have questions regarding clubs, who should I contact?"),
                TextPart("output: For furthermore details and inquiries regarding club, please contact sdsc@uts.edu.my."),
                TextPart("input: What is the aim of Leadpoint in UTS?"),
                TextPart("output: Leadpoint is a student development programme that aims to equip students with essential soft skills and knowledge to enhance their employability and leadership potential. It is a holistic programme that combines leadership training, mentorship, community service, and networking opportunities."),
                TextPart("input: Who is the Vice Chancellor and President of UTS?"),
                TextPart("output: Prof. Datuk Dr. Khairuddin Ab. Hamid. He holds a PhD in Electronic Systems Eng. (Essex), an MSc. in Electrical Eng (Hawaii), and a B. in Electrical Eng. (UTM)."),
                TextPart("input: Who is the Deputy Vice Chancellor (Academic) at UTS?"),
                TextPart("output: That would be Prof. Ir. Ts. Dr. Mohammad Shahril Bin Osman. He has a PhD and a B. Eng (Hons) in Mechanical Engineering from Loughborough University."),
                TextPart("input: Who is responsible for managing the financial and accounting affairs of the university?"),
                TextPart("output: Dr. Rabuan Bin Mantine serves as the Bursar of UTS. He holds a PhD in Finance from UNIMAS, an MBA from OHIO, an Adv. Diploma in Accountancy from UiTM, and is a Chartered Accountant."),
                TextPart("input: Who handles student registration and academic records management?"),
                TextPart("output: That would be Mr Jimmy De Rozario @ Abdul Rahman, CHRC, CHRA, AMC. He holds a BA in HR from UPM and a Dip. in Public Admin from UiTM."),
                TextPart("input: Who is the Dean of the School of Engineering and Technology at UTS?"),
                TextPart("output: The Dean of the School of Engineering and Technology is Associate Professor Dr. Dayang Siti Hazimmah Binti Ali. She holds a PhD in Civil Engineering in Construction Materials from UMP, an MSc. in Materials"),
                TextPart("input: Who is the Dean of the School of Built Environment at UTS?"),
                TextPart("output: That would be Sr. Dr. Kan Fock Kui. He has a PhD in Quantity Surveying from UTM, an MSc in Construction Management from Bath, and a Diploma in Technology (Building) from TARC."),
                TextPart("input: Who is the Dean of the School of Business and Management?"),
                TextPart("output: The Dean of the School of Business and Management is Assoc. Prof. Dr. Winnie Wong Poh Ming. She holds a PhD in Marketing from UNIMAS, an MBA from UMS, and a B. Ed in Education with Economics."),
                TextPart("input: Who is the Dean of the School of Computing and Creative Media?"),
                TextPart("output: That responsibility falls to Ts. Dr. Alan Ting Huong Yong. He holds a PhD from MMU."),
                TextPart("input: Who is the Dean of the School of Foundation Studies?"),
                TextPart("output: That would be Mr. Muhammad Afif Abdul Rani. He holds a Master of Science (Hons) in Applied Physics and Master in Theoretical Physics, along with a Bachelor of Science (Hons) in Applied Physics."),
                TextPart("input: Who is in charge of corporate and relations at UTS?"),
                TextPart("output: That would be Mr. Mohd Ian Daim bin Mahmud. He holds a Bachelor of Corporate Administration (Hons) from UITM."),
                TextPart("input: Who manages human capital and administration at UTS?"),
                TextPart("output: Mdm Nurfaezah binti Sharif is responsible for that. She holds a Bachelor's in Human Resource Development from UNIMAS."),
                TextPart("input: Who is in charge of academic affairs and registry at UTS?"),
                TextPart("output: Mdm Aspa Aida binti Mohamad holds that position. She has a Bachelor of Science in Human Development from UPM."),
                TextPart("input: Who is the head for information technology at UTS?"),
                TextPart("output: Mr. Shukri Abdul Rahman manages information technology. He holds a Bachelor's in Computer Info System Engineering from IIUM."),
                TextPart("input: Who handles maintenance and security at UTS?"),
                TextPart("output: Mr. Lau Hwi Sung takes care of maintenance and security. He holds a Bachelor's in Commerce from Windsor, Canada."),
                TextPart("input: Who is in charge of accounting and finance at UTS?"),
                TextPart("output: Ms. Su Yew Ching manages accounting and finance. She holds a Bachelor's in Commerce Major in Accounting from Queensland, Australia, and is a Chartered Accountant."),
                TextPart("input: Who is in charge student development and services at UTS?"),
                TextPart("output: That responsibility falls to Mr. Lu Yew King. He holds a Bachelor's in Mechanical Engineering from Brighton, England."),
                TextPart("input: Who is responsible for marketing and student recruitment in Kuching?"),
                TextPart("output: Mr. Lau Wee Ming handles that. He has a Master's in Science (Marketing) and a Bachelor's in Business Management (Marketing)."),
                TextPart("input: Who manages marketing and student recruitment in Sibu?"),
                TextPart("output: Mr. Shahminan Bin Samion is responsible for that. He holds a Bachelor of Business Administration Hons Majoring in Human Resource Management."),
                TextPart("input: I have feedback for the app"),
                TextPart("output: For any problem or feedback, please contact crazyfish@gmail.com. Thank you."),
                TextPart("input: Who is in charge of the daily operations and development of the School of Engineering and Technology?"),
                TextPart("output: Associate Professor Dr. Dayang Siti Hazimmah Binti Ali is the Dean of the School of Engineering and Technology at UTS. She holds a PhD in Civil Engineering in Construction Materials from UMP, an MSc. in Materials"),
                TextPart("input: I want to know about the tuition fees and credit hour for the programmes"),
                TextPart("output: Please visit the webpage of UTS for further details."),



                    TextPart(prompt)))

            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(content)
            }

            // Filter unwanted symbols from the response
            val filteredResponse = response.text?.replace("*", "")

            Chat(prompt = filteredResponse ?: "error", bitmap = null, isFromUser = false)
        } catch (e: Exception) {
            Chat(prompt = e.message ?: "error", bitmap = null, isFromUser = false)
        }
    }

    suspend fun getResponseWithImage(
        prompt: String,
        bitmap: Bitmap,
    ): Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-1.0-pro",
            apiKey = api_key,
        )

        return try {
            val inputContent = Content(parts = listOf(
                TextPart(prompt)
            ))

            val response = withContext(Dispatchers.IO) {
                generativeModel.generateContent(inputContent)
            }

            val filteredResponse = response.text?.replace("*", "")

            Chat(prompt = filteredResponse ?: "error", bitmap = null, isFromUser = false)
        } catch (e: Exception) {
            Chat(prompt = e.message ?: "error", bitmap = null, isFromUser = false)
        }
    }
}
