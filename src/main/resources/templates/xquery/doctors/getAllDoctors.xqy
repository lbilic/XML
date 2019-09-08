xquery version "3.1";

declare namespace doctors = "http://www.zis.rs/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/schemes/doctor";

declare namespace users = "http://www.zis.rs/schemes/users";
declare namespace user = "http://www.zis.rs/schemes/user";

<doctors xmlns:doctors="http://www.zis.rs/schemes/doctors" xmlns:voc="http://www.zis.rs/rdf/voc#">{
    for $doctor in fn:doc("/db/rs/zis/doctors.xml")/doctors:doctors/lkr:doctor
    for $user in fn:doc("/db/rs/zis/users.xml")/users:users/user:user
    where $user/@id = $doctor/lkr:user/@lkr:identifier and $user/@active = "true"
    return <lkr:doctor xmlns:lkr="http://www.zis.rs/schemes/doctor" id="{$doctor/@id}">
        <user:user xmlns:user="http://www.zis.rs/schemes/user" id="{$user/@id}">
            {$user/user:ime}
            {$user/user:surname}
            {$user/user:jmbg}
            {$user/user:active}
            {$user/user:username}
        </user:user>
        {$doctor/lkr:type}
        {$doctor/lkr:area_of_protection}
    </lkr:doctor>
}
</doctors>
