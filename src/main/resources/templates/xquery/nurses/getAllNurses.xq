xquery version "3.1";

declare namespace nurses = "http://www.zis.rs/schemes/nurses";
declare namespace nurse = "http://www.zis.rs/schemes/nurse";

declare namespace users = "http://www.zis.rs/schemes/users";
declare namespace user = "http://www.zis.rs/schemes/user";

<nurses xmlns:nurses="http://www.zis.rs/schemes/nurses" xmlns:voc="http://www.zis.rs/rdf/voc#">{
    for $nurse in fn:doc("/db/rs/zis/nurses.xml")/nurses:nurses/nurse:nurse
    for $user in fn:doc("/db/rs/zis/users.xml")/users:users/user:user
    where $user/@id = $nurse/nurse:user/@nurse:identifier
            and $user/@active = "true"
    return <nurse:nurse xmlns:nurse="http://www.zis.rs/schemes/nurse" id="{$nurse/@id}">
        <user:user xmlns:user="http://www.zis.rs/schemes/user" id="{$user/@id}">
            {$user/user:ime}
            {$user/user:surname}
            {$user/user:jmbg}
            {$user/user:active}
            {$user/user:username}
        </user:user>
    </nurse:nurse>
}
</nurses>
