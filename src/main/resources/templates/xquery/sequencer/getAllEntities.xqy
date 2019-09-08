xquery version "3.1";

declare namespace doctors = "http://www.zis.rs/seme/doctors";
declare namespace doctor = "http://www.zis.rs/seme/doctor";

declare namespace referrals = "http://www.zis.rs/seme/referrals";
declare namespace referral = "http://www.zis.rs/seme/referral";

declare namespace drugs = "http://www.zis.rs/seme/drugs";
declare namespace drug = "http://www.zis.rs/seme/drug";

declare namespace reports = "http://www.zis.rs/seme/reports";
declare namespace report = "http://www.zis.rs/seme/report";

declare namespace prescriptions = "http://www.zis.rs/seme/prescriptions";
declare namespace prescription = "http://www.zis.rs/seme/prescription";

declare namespace zd= "http://www.zis.rs/seme/charts";
declare namespace zko="http://www.zis.rs/seme/chart";

declare namespace exams = "http://www.zis.rs/seme/exams";
declare namespace exam = "http://www.zis.rs/seme/exam";

declare namespace me = "http://www.zis.rs/seme/medicinske_sestre";
declare namespace ms = "http://www.zis.rs/seme/medicinska_sestra";

declare namespace ko = "http://www.zis.rs/seme/users";
declare namespace user = "http://www.zis.rs/seme/user";

declare namespace choices = "http://www.zis.rs/seme/choices";
declare namespace choice = "http://www.zis.rs/seme/choice";

let $users := for $user in fn:doc("/db/rs/zis/users.xml")/ko:users/user:user
return $user

let $med_ses := for $med_se in fn:doc("/db/rs/zis/medicinske_sestre.xml")/me:medicinske_sestre/ms:medicinska_sestra
return $med_se

let $prescriptions := for $prescription in fn:doc("/db/rs/zis/prescriptions.xml")/prescriptions:prescriptions/prescription:prescription
return $prescription

let $reports := for $report in fn:doc("/db/rs/zis/reports.xml")/reports:reports/report:report
return $report

let $referrals := for $referral in fn:doc("/db/rs/zis/referrals.xml")/referrals:referrals/referral:referral return $referral

let $drugs := for $drug in fn:doc("/db/rs/zis/drugs.xml")/drugs:drugs/drug:drug return $drug

let $doctors := for $doctor in fn:doc("/db/rs/zis/doctors.xml")/doctors:doctors/doctor:doctor return $doctor

let $zd_kartoni := for $zd_karton in fn:doc("/db/rs/zis/charts.xml")/zd:charts/zko:chart
return $zd_karton

let $exams := for $exam in fn:doc("/db/rs/zis/exams.xml")/exams:exams/exam:exam
return $exam

let $choices := for $choice in fn:doc("/db/rs/zis/choices.xml")/choices:choices/choice:choice
return $choice

return fn:count($doctors) +  fn:count($drugs) +  fn:count($referrals) + fn:count($reports) + fn:count($doctors)
        + fn:count($prescriptions) + fn:count($zd_kartoni) + fn:count($exams) + fn:count($med_ses) + fn:count($users)
        + fn:count($choices)