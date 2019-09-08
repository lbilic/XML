xquery version "3.1";

declare namespace doctors = "http://www.zis.rs/xml/schemes/doctors";
declare namespace doctor = "http://www.zis.rs/xml/schemes/doctor";

declare namespace referrals = "http://www.zis.rs/xml/schemes/referrals";
declare namespace referral = "http://www.zis.rs/xml/schemes/referral";

declare namespace drugs = "http://www.zis.rs/xml/schemes/drugs";
declare namespace drug = "http://www.zis.rs/xml/schemes/drug";

declare namespace reports = "http://www.zis.rs/xml/schemes/reports";
declare namespace report = "http://www.zis.rs/xml/schemes/report";

declare namespace prescriptions = "http://www.zis.rs/xml/schemes/prescriptions";
declare namespace prescription = "http://www.zis.rs/xml/schemes/prescription";

declare namespace zd= "http://www.zis.rs/xml/schemes/charts";
declare namespace zko="http://www.zis.rs/xml/schemes/chart";

declare namespace exams = "http://www.zis.rs/xml/schemes/exams";
declare namespace exam = "http://www.zis.rs/xml/schemes/exam";

declare namespace me = "http://www.zis.rs/xml/schemes/nurses";
declare namespace ms = "http://www.zis.rs/xml/schemes/nurse";

declare namespace ko = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

declare namespace choices = "http://www.zis.rs/xml/schemes/choices";
declare namespace choice = "http://www.zis.rs/xml/schemes/choice";

let $users := for $user in doc("/db/xml/schemes/users.xml")/ko:users/user:user
return $user

let $med_ses := for $med_se in doc("/db/xml/schemes/nurses.xml")/me:nurses/ms:nurse
return $med_se

let $prescriptions := for $prescription in fn:doc("/db/xml/schemes/prescriptions.xml")/prescriptions:prescriptions/prescription:prescription
return $prescription

let $reports := for $report in doc("/db/xml/schemes/reports.xml")/reports:reports/report:report
return $report

let $referrals := for $referral in doc("/db/xml/schemes/referrals.xml")/referrals:referrals/referral:referral return $referral

let $drugs := for $drug in doc("/db/xml/schemes/drugs.xml")/drugs:drugs/drug:drug return $drug

let $doctors := for $doctor in oc("/db/xml/schemes/doctors.xml")/doctors:doctors/doctor:doctor return $doctor

let $zd_kartoni := for $zd_karton in doc("/db/xml/schemes/charts.xml")/zd:charts/zko:chart
return $zd_karton

let $exams := for $exam in doc("/db/xml/schemes/exams.xml")/exams:exams/exam:exam
return $exam

let $choices := for $choice in doc("/db/xml/schemes/choices.xml")/choices:choices/choice:choice
return $choice

return fn:count($doctors) +  fn:count($drugs) +  fn:count($referrals) + fn:count($reports) + fn:count($doctors)
        + fn:count($prescriptions) + fn:count($zd_kartoni) + fn:count($exams) + fn:count($med_ses) + fn:count($users)
        + fn:count($choices)