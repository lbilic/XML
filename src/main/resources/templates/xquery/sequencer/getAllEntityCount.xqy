xquery version "3.1";

declare namespace doctors = "http://www.zis.rs/schemes/doctors";
declare namespace doctor = "http://www.zis.rs/schemes/doctor";

declare namespace referrals = "http://www.zis.rs/schemes/referrals";
declare namespace referral = "http://www.zis.rs/schemes/referral";

declare namespace drugs = "http://www.zis.rs/schemes/drugs";
declare namespace drug = "http://www.zis.rs/schemes/drug";

declare namespace reports = "http://www.zis.rs/schemes/reports";
declare namespace report = "http://www.zis.rs/schemes/report";

declare namespace prescriptions = "http://www.zis.rs/schemes/prescriptions";
declare namespace prescription = "http://www.zis.rs/schemes/prescription";

declare namespace zd= "http://www.zis.rs/schemes/charts";
declare namespace zko="http://www.zis.rs/schemes/chart";

declare namespace exams = "http://www.zis.rs/schemes/exams";
declare namespace exam = "http://www.zis.rs/schemes/exam";

declare namespace me = "http://www.zis.rs/schemes/nurses";
declare namespace ms = "http://www.zis.rs/schemes/nurse";

declare namespace ko = "http://www.zis.rs/schemes/users";
declare namespace user = "http://www.zis.rs/schemes/user";

declare namespace choices = "http://www.zis.rs/schemes/choices";
declare namespace choice = "http://www.zis.rs/schemes/choice";

let $users := for $user in doc("/db/rs/zis/users.xml")/ko:users/user:user
return $user

let $med_ses := for $med_se in doc("/db/rs/zis/nurses.xml")/me:nurses/ms:nurse
return $med_se

let $prescriptions := for $prescription in doc("/db/rs/zis/prescriptions.xml")/prescriptions:prescriptions/prescription:prescription
return $prescription

let $reports := for $report in doc("/db/rs/zis/reports.xml")/reports:reports/report:report
return $report

let $referrals := for $referral in doc("/db/rs/zis/referrals.xml")/referrals:referrals/referral:referral return $referral

let $drugs := for $drug in doc("/db/rs/zis/drugs.xml")/drugs:drugs/drug:drug return $drug

let $doctors := for $doctor in doc("/db/rs/zis/doctors.xml")/doctors:doctors/doctor:doctor return $doctor

let $zd_kartoni := for $zd_karton in doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
return $zd_karton

let $exams := for $exam in doc("/db/rs/zis/exams.xml")/exams:exams/exam:exam
return $exam

let $choices := for $choice in doc("/db/rs/zis/choices.xml")/choices:choices/choice:choice
return $choice

return fn:count($doctors) +  fn:count($drugs) +  fn:count($referrals) + fn:count($reports) + fn:count($doctors)
        + fn:count($prescriptions) + fn:count($zd_kartoni) + fn:count($exams) + fn:count($med_ses) + fn:count($users)
        + fn:count($choices)