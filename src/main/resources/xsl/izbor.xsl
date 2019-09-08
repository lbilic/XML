<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                xmlns:choices="http://www.zis.rs/xml/schemes/choices"
                xmlns:choice="http://www.zis.rs/xml/schemes/choice"
                xmlns:zko="http://www.zis.rs/xml/schemes/chart"
                xmlns:lkr="http://www.zis.rs/xml/schemes/doctor"
                xmlns:user="http://www.zis.rs/xml/schemes/user">

    <xsl:template match="/">

        <html>
            <head>
                <style type="text/css">
                    span {
                    display:table;
                    margin:0 auto;

                    }
                    div {
                    margin: 10px 20px 10px 20px;
                    }
                    p {
                    margin: 0px 0px 0px 0px;
                    font-size:12px;
                    }
                    td {
                    font-size:12px;
                    }
                </style>
                <title> <b>Izjava o choiceu i promeni izabranog doctora </b></title>
            </head>
            <body>
                <div >
                <p  align="right">Obrazac IL</p>
                <p align="left" style="color:DimGray"><xsl:value-of select="//choice:form_type"/></p>
                <p align="left">___________</p>
                <p>Tip obrasca:</p>
                </div>

                <p align="center" style="color:DimGray"><xsl:value-of select="//choice:naziv_ustanove"/></p>
                <p align="center">_____________________________________________________</p>
                <p align="center">Naziv zdravstvene ustanove: </p><br/>


                <div style="width: 100%;">
                    <div style="float:left; width: 50%;" >
                        <p style="background-color:gray;" align="center" > <b> Osigurano lice </b></p><br/>
                        <p style="color:DimGray" align="center"><xsl:value-of select="//choice:insured_person//zko:chart//zko:insured_person//zko:surname"/>
                            <span style="color:white">-</span><xsl:value-of select="//choice:insured_person//zko:chart//zko:insured_person//zko:name"/></p>
                        <p align="left">__________________________________________________</p>
                        <p align="center">(surname i name)</p><br/>


                        <p style="color:DimGray" align="center"><xsl:value-of select="//choice:insured_person//zko:chart/@jmbg"/></p>
                        <p align="left">__________________________________________________</p>
                        <p align="center">(JMBG)</p><br/>

                        <p style="color:DimGray" align="center"><xsl:value-of select="//choice:insured_person//zko:chart/@lbo"/></p>
                        <p align="left">__________________________________________________</p>
                        <p align="center">(LBO)</p><br/>

                        <p style="color:DimGray" align="center">
                            <xsl:value-of select="//choice:insured_person//zko:chart//zko:address//zko:street"/>
                            <span style="color:white">-</span>
                            <xsl:value-of select="//choice:insured_person//zko:chart//zko:address//zko:number" />
                            <span> , </span>
                            <xsl:value-of select="//choice:insured_person//zko:chart//zko:address//zko:zip_code" />
                            <span> , </span>
                            <xsl:value-of select="//choice:insured_person//zko:chart//zko:address//zko:town" />
                            <span> , </span>
                            <xsl:value-of select="//choice:insured_person//zko:chart//zko:address//zko:municipality" />
                        </p>
                        <p align="left">__________________________________________________</p>
                        <p align="center">(address: street i number, postanski number, town i opština)</p><br/>

                        <p style="color:DimGray" align="center"> <xsl:value-of select="//choice:insured_person//zko:chart//zko:insured_person//zko:sex"/>
                        </p>
                        <p align="left">__________________________________________________</p>
                        <p align="center">(sex)</p><br/>

                        <p style="color:DimGray" align="center"> <xsl:value-of select="//choice:insured_person//zko:chart//zko:phone_number"/>
                        </p>
                        <p align="left">__________________________________________________</p>
                        <p align="center">(number telefona)</p><br/>

                    </div>
                    <div style="float:right;">
                        <p style="background-color:gray;" align="center" > <b> Izabrani doctor </b></p><br/>
                        <p style="color:DimGray" align="center"><xsl:value-of select="//choice:doctor//lkr:doctor//user:surname"/>
                            <span style="color:white">-</span><xsl:value-of select="//choice:doctor//lkr:doctor//user:name"/></p>
                        <p align="right">__________________________________________________</p>
                        <p align="center">(surname i name)</p><br/>

                        <p style="color:DimGray" align="center"><xsl:value-of select="//choice:doctor//lkr:area_of_protection"/></p>
                        <p align="right">__________________________________________________</p>
                        <p align="center">(oblast zaštite)</p><br/>

                        <p style="color:DimGray" align="center"><xsl:value-of select="//choice:doctor//lkr:doctor//@id"/></p>
                        <p align="right">__________________________________________________</p>
                        <p align="center">(ID oznaka)</p><br/><br/><br/>

                        <p style="background-color:gray;" align="center" > <b> Prethodno izabrani doctor </b></p><br/>
                        <p style="color:DimGray" align="center"><xsl:value-of select="//choice:prosli_doctor//user:user//user:surname"/>
                            <span style="color:white">-</span><xsl:value-of select="//choice:prosli_doctor//user:user//user:name"/></p>
                        <p align="right">__________________________________________________</p>
                        <p align="center">(surname i name)</p><br/>

                        <p style="color:DimGray" align="center"><xsl:value-of select="//choice:reason_for_change"/></p>
                        <p align="right">__________________________________________________</p>
                        <p align="center">(razlog promene)</p><br/>

                        <p style="color:DimGray" align="center"><xsl:value-of select="//choice:prosli_doctor//lkr:doctor//@id"/></p>
                        <p align="right">__________________________________________________</p>
                        <p align="center">(ID oznaka)</p><br/>
                    </div>
                </div>
                <div style="clear:both"></div>

                <br/><br/><br/>

                <table>
                    <tr>
                        <td><xsl:value-of select="//choice:date"/></td>
                    </tr>
                    <tr>
                        <td>__________________________<span style="color:white">--------------------</span></td>
                        <td>__________________________ <span style="color:white">--------------------</span></td>
                        <td>__________________________ <span style="color:white">--------------------</span></td>
                    </tr>
                    <tr>
                        <td >Datum</td>
                        <td>Potpis zdravstvenog radnika</td>
                        <td>Potpis nosioca osiguranja</td>
                    </tr>
                </table>

            </body>

        </html>
    </xsl:template>


</xsl:stylesheet>