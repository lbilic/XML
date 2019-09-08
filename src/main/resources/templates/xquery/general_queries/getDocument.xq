xquery version "3.1";

declare namespace referrals = "http://www.zis.rs/xml/schemes/referrals";
declare namespace referral = "http://www.zis.rs/xml/schemes/referral";

declare namespace reports = "http://www.zis.rs/xml/schemes/reports";
declare namespace report = "http://www.zis.rs/xml/schemes/report";

declare namespace prescriptions = "http://www.zis.rs/xml/schemes/prescriptions";
declare namespace prescription = "http://www.zis.rs/xml/schemes/prescription";

declare namespace choices = "http://www.zis.rs/xml/schemes/choices";
declare namespace choice = "http://www.zis.rs/xml/schemes/choice";

declare variable $ol := "%1$s";

let $prescriptions := for $prescription in doc("/db/xml/schemes/prescriptions.xml")/prescriptions:prescriptions/prescription:prescription
where $prescription//prescription:insured_person//@prescription:identifier = $ol
return $prescription

let $reports := for $report in doc("/db/xml/schemes/reports.xml")/reports:reports/report:report
where $report//report:insured_person//@report:identifier = $ol
return $report

let $referrals := for $referral in doc("/db/xml/schemes/referrals.xml")/referrals:referrals/referral:referral
where $referral//referral:insured_person//@referral:identifier = $ol
return $referral

let $choices := for $choice in doc("/db/xml/schemes/choices.xml")/choices:choices/choice:choice
where $choice//choice:insured_person//@choice:identifier = $ol
return $choice

return
    <documents> {
        ($prescriptions,$referrals, $reports,$choices)
    }
    </documents>
