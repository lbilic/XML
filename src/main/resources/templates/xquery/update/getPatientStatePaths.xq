xquery version "3.1";

declare namespace sp = "http://www.zis.rs/xml/schemes/exam_states";
declare namespace stp = "http://www.zis.rs/xml/schemes/exam_state";

declare namespace functx = "http://www.functx.com";

declare function functx:last-node
($nodes as node()*) as node()? {

    ($nodes/.)[fn:last()]
};

declare function functx:index-of-node
($nodes as node()*,
        $nodeToFind as node()) as xs:integer* {

    for $seq in (1 to fn:count($nodes))
    return $seq[$nodes[$seq] is $nodeToFind]
};

declare function functx:path-to-node-with-pos
($node as node()?) as xs:string {

    fn:string-join(
            for $ancestor in $node/ancestor-or-self::*
            let $sibsOfSameName := $ancestor/../*[fn:name() = fn:name($ancestor)]
            return fn:concat(fn:name($ancestor),
                    if (fn:count($sibsOfSameName) <= 1)
                    then ''
                    else fn:concat(
                            '[', functx:index-of-node($sibsOfSameName, $ancestor), ']'))
            , '/')
};

declare variable $states :=
    for $state in doc("/db/rs/zis/exam_states.xml")/sp:exam_states/stp:exam_state
    where $state//@patient = "%1$s"
    return $state;

let $state := functx:last-node($states)
return fn:concat("/", functx:path-to-node-with-pos($state))
