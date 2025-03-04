# VU ICU Management App

VU is a graduate project aimed at revolutionizing patient monitoring and healthcare delivery in intensive care units (ICUs). It enhances communication between healthcare teams, automates vital sign monitoring, and provides real-time access to patient data, empowering doctors and nurses to deliver high-quality care efficiently.
### Key Features and Goals
- :chart_with_upwards_trend: Real-time data streaming for continuous patient monitoring.
- :robot: Automated vital sign recording to reduce manual workload and errors.
- :file_folder: Electronic record-keeping for centralized and accessible patient information.
- :satellite: Remote monitoring capabilities to facilitate healthcare provider mobility.
### Technologies Used
- **Frontend**: Flutter
- **Backend**: Spring Boot
- **Database**: MySQL
- **Hardware**: ESP32-CAM  
- **Additional Technologies**: Computer vision algorithm

---
> ## Development Methodology
> ### Why Waterfall Was Chosen
> The **Waterfall methodology** was selected for the initial version of the VU ICU Management App due to the specific demands of healthcare systems:
> - **Interdependent Components**: Features like real-time monitoring require tightly integrated hardware, backend, and databases. Incremental deployment (as in Agile) could cause instability.
> - **High Stakes**: Errors in healthcare can be life-threatening, and Waterfall’s comprehensive testing helps minimize risks.
> - **System-Wide Testing**: Ensures all components are validated end-to-end before deployment.
> ### Why Agile May Not Be Suitable (Initially)
> Agile was less suitable for VU due to:
> - **Interdependencies**: Incremental deployment could risk system instability.
> - **Regulatory Needs**: Upfront comprehensive testing is required, which Agile's iterative approach does not fully support.
> - **High Stakes**: A fully functional system is necessary from the start, aligning with Waterfall’s structured process.
> ### Future Transition to Agile
> For future versions, **Agile methodologies** will be incorporated to:
> - **Add New Features**: Iterative development for non-critical enhancements (e.g., predictive analytics, IoT integration).
> - **Improve User Experience**: Use continuous feedback to refine UI/UX.
> - **Iterative Testing**: Gradual testing and deployment of new features, focusing on less critical areas to reduce risks.
---

# 🔄 Software Development LifeCycle

## 1. 📅 Planning (Project Beginnings)
### 1.1 System Analysis Activities
During the planning phase of the VU ICU Management App, we conducted a series of system analysis activities to ensure the project's success. These activities were aimed at gaining a deeper understanding of ICU operations, defining requirements, and aligning the system with the needs of healthcare providers and regulatory standards. Our team visited hospitals and interacted with healthcare professionals to identify key challenges and develop targeted solutions.
#### **1.1.1 Gather Detailed Information 📋**
To gain a comprehensive understanding of the challenges and requirements, we employed multiple methods:
- **Interviews**: We conducted in-depth interviews with doctors, nurses, and administrators to understand how system operated, their needs, pain points, and expectations for the system.
- **Documentation Review**: we examined the documents they use, including patient files, reports (🔗[View Vital Signs Report](DOCs/Media/vital_signs_report.jpg)) to see how data managed. We studied these to simulate these documents and reports in our system, ensuring our system mirrors real-world data handling.
- **Observing Business Processes**: We visited hospitals to observe ICU workflows firsthand, identifying inefficiencies in manual data recording, communication gaps, and workflow bottlenecks.
- **Researching Vendors**: We researched existing systems that were similar to our idea (EX: Dragger Kappa) to understand how they implemented solutions. By analyzing their approaches, we were able to apply and enhance some of their techniques, ultimately developing a more effective solution for our project.
- **Comments and Suggestions**: We collected feedback from stakeholders through surveys, feedback sessions, and brainstorming, ensuring their input was incorporated into the system design.

#### **1.1.2 Evaluate Requirements with Users 👥**
We presented the initial requirements and UI designs to stakeholders for feedback:
- Incorporated their suggestions to refine the system and ensure it meets their needs.
- Conducted iterative reviews to align the system with stakeholder expectations.

### **1.1.3 Challenges and Solutions 🛠️**
After conducting all the system analysis activities, we identified key challenges and provided tailored solutions to address them:
| **Challenge**                                   | **Solution**                                          |
|-------------------------------------------------|-------------------------------------------------------|
| Manual monitoring with potential for errors     | Automated vital sign recording                        |
| Paper-based records hindering communication     | Electronic record-keeping                             |
| Continuous monitoring increasing stress         | Remote monitoring and real-time streaming via ESP32-CAM|
| Compromised patient care quality                | Enhanced communication and instant notifications      |

#### **1.1.4 Engage Stakeholders 🤝**
We engaged both **internal** and **external** stakeholders to ensure comprehensive system alignment:
- **Internal Stakeholders**: Doctors, nurses, administrators, and IT teams provided crucial input on system requirements and usability.
- **External Stakeholders**: Vendors, technology providers, and regulatory bodies helped ensure hardware compatibility and legal compliance.
Clear communication channels were established to gather feedback and address concerns, ensuring the system met operational and regulatory needs.

#### **1.1.5 Define Requirements 📝**
Based on the gathered information, we outlined both **functional** and **non-functional requirements**:
- **Functional Requirements**: Real-time patient monitors streaming, automated vital sign extraction and recording, shift management, task management, and instant notifications.
- **Non-Functional Requirements**: Performance, reliability, security, and scalability.

### 1.2 First Team Meeting
we discussed the challengss and brainstormed potential solutions and visualized our ideas on paper. We created rough sketches of the UI on paper served as the first visual representation of our app, highlighting the essential features and layout.
- 🔗[View Figma Sketching Process](https://www.figma.com/board/ZQrooRtj2tLBjVQd0YZJbc/VUE-SDLC?node-id=0-1&t=mkKWM7DL2DmUfKcb-1)

we distributed tasks among team members based on their expertise and roles :
- 🔗[View Notion Teamspace](DOCs/Media/Notion%20VU%20Teamspace.png)

> ### The Inspiration Behind "VU"
> Befor moving to analysis phase lets see what is behind name "VU".
> - "VU" derives from the word "view", emphasizing our focus on providing healthcare providers (doctors and nurses) with a clear view and monitoring capability of patient conditions remotely.
> - The characters "V" and "U" resemble peaks often seen in ECG (Electrocardiogram) readings as you see :
> <img src="DOCs/Media/Name%20Inspiration.png" alt="Name Inspiration" width="50%" />

## Analysis
During the analysis phase, our team focused on thoroughly understanding the project's requirements and processes : 
#### Functional and Non-Functional Requirements
#### Business Model Exploration
#### Database Analysis and Design
We designed the database schema, ensuring it meets the project requirements. We created both logical and physical Entity-Relationship (ER) diagrams to represent and relationships between different entities in the system.
- 🔗[View DataBase Script And Diagrams](DataBase-mysql)
#### UML Diagrams
- 🔗[View UML Diagrams](DOCs/UML%20Diagrams)

## Design
- 🔗[View UI-UX Design](https://www.figma.com/design/cEh3XCKwOgDhwkyOG3LdCw/VU-App?node-id=190-460&t=jT2b3lBsnHeoM8q3-1)

## Implementation
#### Backend
We used springboot framework to implement the endpoints of our app (spring rest , spring data jdbc).
#### Data Base
We used mysql DBMS (mysql workbench) to implement the DB.
