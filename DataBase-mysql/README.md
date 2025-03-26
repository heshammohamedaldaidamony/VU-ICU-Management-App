# VU ICU Management App - Data Base SubReadme
This document provides an in-depth look at the design and structure of the database used in the Vital Signs Management system.  

## Overview
When a **patient** is admitted to the **ICU**, a nurse assigns them to a **monitoring device** that continuously tracks their vital signs. This real-time data is stored in the **VitalSignReport** table, capturing time-series data for ongoing analysis. The system automatically records vital signs and flags any abnormal events, such as spikes in heart rate or drops in oxygen saturation, which are stored in the **AbnormalEvents** entity.

**Shifts are** defined for both **Doctor** and **Nurse** roles. Doctors supervise specific **units**, while nurses oversee individual patients. A consultant doctor manages the entire ICU. **Tasks** are assigned based on staff availability, with doctors able to delegate responsibilities to nurses during their shifts. To ensure timely medical intervention, doctors and nurses are notified of abnormal vital signs or emergencies in real time via the Notification entity, streamlining communication and response during critical situations.

Based on the entity identifying techniques (brainstorming with teammates and identifying nouns), here is the database documentation:  
🔗[DataBase Documentation](DataBase-mysql/database_documentation.doc)  

>⚠️ Important Notice  
>In our ICU management system, we offer two main modes of operation:
>1. Standalone System:  
This mode is for scenarios where our system is implemented as the primary system in a hospital. In this case, we create all necessary tables such as patients, doctors, and nurses directly within our database.  
>2. Integration with Existing Systems:  
If the hospital already has an existing system, our system can integrate with it. In this mode, we link to the existing tables and data (like patients, doctors, and nurses) without duplicating them in our database.  

## DB Design
In the ICU Management System, we use a hybrid database approach, combining **SQL (MySQL)** and **NoSQL (MongoDB)** to balance the needs of structured and unstructured data. This approach leverages the strengths of each database type to optimize performance, scalability, and flexibility.
- **SQL (MySQL)** is used for structured, relational data where integrity and transactional support are important: patient data, medical staff, shift management, and task assignments.
- **NoSQL (MongoDB)** is used for unstructured, high-volume data like real-time vital signs, and abnormal events, where flexibility and scalability are critical.
