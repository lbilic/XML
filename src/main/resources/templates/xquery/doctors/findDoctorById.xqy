xquery version "3.1";

declare namespace doctors = "http://www.zis.rs/xml/schemes/doctors";
declare namespace lkr = "http://www.zis.rs/xml/schemes/doctor";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

for $doctor in doc("/db/xml/schemes/doctors.xml")/doctors:doctors/lkr:doctor
for $user in doc("/db/xml/schemes/users.xml")/users:users/user:user
where $user//@id = $doctor//lkr:user//@lkr:identifier and $doctor//@id = "%1$s"
        and $user//@active = "true"

return <lkr:doctor xmlns:lkr="http://www.zis.rs/xml/schemes/doctor" xmlns:voc="http://www.zis.rs/rdf/voc#" id="{$doctor/@id}">
    <user:user xmlns:user="http://www.zis.rs/xml/schemes/user" id="{$user//@id}">
        {$user//user:name}
        {$user//user:surname}
        {$user//user:jmbg}
        {$user//user:active}
        {$user//user:username}
    </user:user>
    {$doctor//lkr:type}
    {$doctor//lkr:area_of_protection}
</lkr:doctor>