INSERT INTO Patient(patient_id, name) VALUES 
(10032, 'Jocob Brinstone'),
(72546, 'Laura Wickerson'),
(49267, 'Mylan Grand'),
(54891, 'Emma Cartright'),
(65748, 'Liam Smith'),
(76859, 'Sophia Johnson');

INSERT INTO User(username, password, uid, type, name) VALUES
('mhossain', 'drpassword123', 1, 'physician', "Dr. Mariam Hossain"),
('jnurse', 'nursepassword', 2, 'nurse', "Nurse One"),
('recep1', 'receptionpass', 3, 'receptionist', 'Receptionist One'),
('physician2', 'physicianpass', 4, 'physician', 'Dr. John Doe'),
('nurse2', 'nursepass2', 5, 'nurse', 'Nurse Two');

INSERT INTO PhysicianNurse(physician_id, nurse_id) VALUES
(1, 2),
(4, 5);

INSERT INTO PhysicianPatient(patient_id, physician_id) VALUES
(72546, 1),
(10032, 1),
(49267, 1),
(54891, 4),
(65748, 4),
(76859, 4);

INSERT INTO LabTest(patient_id, test_type, date, result) VALUES 
(10032, 'Hemoglobin', '2023-12-01', 11.6),
(10032, 'Hemoglobin', '2023-10-02', 12.8),
(10032, 'Hemoglobin', '2023-01-04', 12.4),
(10032, 'Calcium', '2023-12-01', 138),
(10032, 'Calcium', '2023-10-02', 149),
(10032, 'Calcium', '2023-01-04', 176),
(10032, 'Ferritin', '2023-12-01', 8),
(10032, 'Ferritin', '2023-10-02', 54),
(10032, 'Ferritin', '2023-01-04', 62);

INSERT INTO LabTest(patient_id, test_type, date, result) VALUES 
(72546, 'Hemoglobin', '2023-01-18', 6.6),
(72546, 'Hemoglobin', '2023-02-21', 10.4),
(72546, 'Hemoglobin', '2023-04-15', 12.4),
(72546, 'Calcium', '2023-01-18', 169),
(72546, 'Calcium', '2023-02-21', 219),
(72546, 'Calcium', '2023-04-15', 176),
(72546, 'Ferritin', '2023-01-18', 78),
(72546, 'Ferritin', '2023-02-21', 154),
(72546, 'Ferritin', '2023-04-15', 202);

INSERT INTO LabTest(patient_id, test_type, date, result) VALUES 
(49267, 'Hemoglobin', '2023-01-06', 21.6),
(49267, 'Hemoglobin', '2023-02-14', 17.8),
(49267, 'Hemoglobin', '2023-04-03', 19.4),
(49267, 'Calcium', '2023-01-06', 158),
(49267, 'Calcium', '2023-02-14', 189),
(49267, 'Calcium', '2023-04-03', 176),
(49267, 'Ferritin', '2023-01-06', 78),
(49267, 'Ferritin', '2023-02-14', 92),
(49267, 'Ferritin', '2023-04-03', 81);

INSERT INTO LabTest(patient_id, test_type, date, result) VALUES 
(54891, 'Hemoglobin', '2023-03-11', 13.2),
(54891, 'Calcium', '2023-03-11', 142),
(54891, 'Ferritin', '2023-03-11', 45),
(65748, 'Hemoglobin', '2023-02-20', 15.8),
(65748, 'Calcium', '2023-02-20', 132),
(65748, 'Ferritin', '2023-02-20', 51),
(76859, 'Hemoglobin', '2023-01-28', 14.4),
(76859, 'Calcium', '2023-01-28', 147),
(76859, 'Ferritin', '2023-01-28', 60);

INSERT INTO VisitRecord(patient_id, date, notes, physician_id, time, location) VALUES
(72546, '2023-06-21', 'Laura was having abdominal pain for the past three days. She has problems in digestion as well. Dr. Hossain advised Laura not to take pain killers. Instead, she was asked to take complete rest. Laura was asked to come back and see the doctor again after three days, if the pain does not go away.', 1, "1:00PM", "Dallas, TX"),
(10032, '2023-05-21', 'Jocob mentioned slight fatigue. Dr. Hossain recommended more rest and a balanced diet.', 1, "2:00PM", "DFW, TX"),
(49267, '2023-06-05', 'Mylan complained about frequent headaches. Dr. Hossain prescribed a light painkiller and suggested a follow-up after a week.', 1, "10:00AM", "Arlington, TX"),
(54891, '2023-07-10', 'Emma reported chest discomfort. Dr. Doe recommended a series of tests and advised against strenuous activities.', 4, '3:00PM', 'Dallas, TX'),
(65748, '2023-08-15', 'Liam had a minor injury on his left arm. The wound was cleaned and bandaged by Dr. Doe.', 4, '11:00AM', 'Fort Worth, TX'),
(76859, '2023-09-05', 'Sophia had an allergic reaction to a food item. Dr. Doe prescribed an antihistamine and advised her to maintain a food diary.', 4, '4:30PM', 'Plano, TX')