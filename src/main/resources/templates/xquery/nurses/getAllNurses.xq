xquery version "3.1";

declare namespace nurses = "http://www.zis.rs/xml/schemes/nurses";
declare namespace nurse = "http://www.zis.rs/xml/schemes/nurse";

declare namespace users = "http://www.zis.rs/xml/schemes/users";
declare namespace user = "http://www.zis.rs/xml/schemes/user";

<nurses xmlns:nurses="http://www.zis.rs/xml/schemes/nurses" xmlns:voc="http://www.zis.rs/xml/rdf/voc#">{
    for $nurse in doc("/db/rs/zis/nurses.xml")/nurses:nurses/nurse:nurse
    for $user in doc("/db/rs/zis/users.xml")/users:users/user:user
    where $user//@id = $nurse//nurse:user//@nurse:identifier
            and $user//@active = "true"
    return <nurse:nurse xmlns:nurse="http://www.zis.rs/xml/schemes/nurse" id="{$nurse//@id}">
        <user:user xmlns:user="http://www.zis.rs/xml/schemes/user" id="{$user//@id}">
            {$user//user:name}
            {$user//user:surname}
            {$user//user:jmbg}
            {$user//user:active}
            {$user//user:username}
        </user:user>
    </nurse:nurse>
}
</nurses>
