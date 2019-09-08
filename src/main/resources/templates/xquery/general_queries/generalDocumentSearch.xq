xquery version "3.1";

declare namespace reports = "http://www.zis.rs/schemes/reports";
declare namespace report = "http://www.zis.rs/schemes/report";

declare namespace prescriptions = "http://www.zis.rs/schemes/prescriptions";
declare namespace prescription = "http://www.zis.rs/schemes/prescription";

declare namespace referrals = "http://www.zis.rs/schemes/referrals";
declare namespace referral = "http://www.zis.rs/schemes/referral";

declare namespace choices = "http://www.zis.rs/schemes/choices";
declare namespace choice = "http://www.zis.rs/schemes/choice";

declare function local:find-prescriptions($text as xs:string) as item()* {
    let $prescriptions := for $prescription in fn:doc("/db/rs/zis/prescriptions.xml")/prescriptions:prescriptions/prescription:prescription
    return
        if (fn:contains($prescription/prescription:description/text(), $text))
        then fn:data($prescription/@id)
        else ()
    return $prescriptions
};

declare function local:find-referrals($text as xs:string) as item()* {
    let $referrals := for $referral in fn:doc("/db/rs/zis/referrals.xml")/referrals:referrals/referral:referral
    return
        if (fn:contains($referral/referral:misljenje/text(), $text))
        then fn:data($referral/@id)
        else ()
    return $referrals
};

declare function local:find-reports($text as xs:string) as item()* {
    let $reports := for $report in fn:doc("/db/rs/zis/reports.xml")/reports:reports/report:report
    return
        if (fn:contains($report/report:anamnesis/text(), $text) or
                fn:contains($report/report:therapy/text(), $text))
        then fn:data($report/@id)
        else ()
    return $reports
};

declare function local:find-choices($text as xs:string) as item()* {
    let $choices := for $choice in fn:doc("/db/rs/zis/choices.xml")/choices:choices/choice:choice
    return
        if (fn:contains($choice//text(), $text))
        then fn:data($choice/@id)
        else ()
    return $choices
};

let $prescriptions := local:find-prescriptions("%1$s")
let $referrals := local:find-referrals("%1$s")
let $reports := local:find-reports("%1$s")
let $choices := local:find-choices("%1$s")
return ($referrals, $prescriptions, $reports, $choices)
