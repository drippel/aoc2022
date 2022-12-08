package com.github.advent

import scala.collection.mutable

object Day08b {

  def main(args: Array[String]): Unit = {

    Console.out.println("day08b...")

    var lines = data.split("\n").map(_.trim).toList

    val trees = parse( lines )

    Console.out.println("parsed...")

    val ss = score(trees)

    Console.out.println(ss)

    val sss = ss.values.toList.sorted.reverse

    Console.out.println(sss.head)
  }

  def score( grid : Array[Array[Int]] ) : Map[(Int,Int),Int] = {

    val mx = grid(0).size
    val my = grid.size

    val smap = mutable.HashMap[(Int,Int),Int]()

    for( y <- 0 until my ) {
      for( x <- 0 until mx ) {

        // Console.out.println( s"$y $x" );
        // Console.out.println( s"${ grid(y)(x)} " );

        // get the directions
        val ns = walk( grid, North(), (x,y) )
        val es = walk( grid, East(), (x,y) )
        val ss = walk( grid, South(), (x,y) )
        val ws = walk( grid, West(), (x,y) )

        // Console.out.println( s"$ns * $es * $ss * $ws" );
        val s = ns * es * ss * ws
        // Console.out.println( s"$s" );

        smap((x,y)) = s

      }
    }

    smap.toMap

  }

  def walk( grid : Array[Array[Int]], dir : Dir, start : (Int,Int) ) : Int = {
    val mx = grid(0).size
    val my = grid.size

    def innerWalk( pos : (Int,Int), accum : List[(Int,Int)] ) : List[(Int,Int)] = {

      if( pos._1 < 0 || pos._1 >= mx || pos._2 < 0 || pos._2 >= my ) {
        accum
      }
      else {
        val na = accum :+ pos
        val np = (pos._1 + dir.x, pos._2 + dir.y)
        innerWalk( np, na )
      }

    }

    val coords = innerWalk( (start._1 + dir.x, start._2 + dir.y), List())

    // now lets see how many trees they can see
    def seen( cs : List[(Int,Int)], count : Int ) : Int = {

      if( cs.isEmpty ) {
        count
      }
      else {
        val nc = count + 1
        val p = cs.head
        if( grid(start._2)(start._1) <= grid(p._2)(p._1)) {
          nc
        }
        else {
          seen( cs.tail, nc )
        }
      }

    }

    seen( coords, 0 )

  }

  class Dir( val x : Int, val y : Int )
  case class North() extends Dir( 0, -1 )
  case class South() extends Dir( 0, 1 )
  case class East() extends Dir( 1, 0 )
  case class West() extends Dir( -1, 0 )

  val Dirs = List(North(), South(), East(), West() )

  def parse( lines : List[String] ) : Array[Array[Int]] = {

    val mx = lines(0).size
    val my = lines.size

    val grid = Array.ofDim[Int](mx,my)

    val lzs = lines.zipWithIndex
    for( lz <- lzs ) {

      val l = lz._1.zipWithIndex
      for( p <- l ) {
        grid( lz._2 )( p._2 ) = p._1.toString.toInt
      }
    }

    grid
  }

  val test = """30373
                25512
                65332
                33549
                35390"""
  
  val data = """002220021312231312222033343421330303240001434141231331323404031022114013434333010230221020221020022
               121122120202012020121043013102221221435521112422443242343123513341010302410221342010223030013102200
               000102113102200232304033323331230042243225545423222145155353323244444103030341114102200110230210210
               210000210011313230124421403131035424212553341224354235111554525424232312243334330011232213102110221
               011221213013233101202214421014211444113354135545433143234341354155455431402040121341332111001213200
               102011200323032042010300400433425523314114545134123354514144125244221425322110331241203233303212321
               102333112233124011310312033433351241332441511153434514142551252434413454525133430430232301313012101
               211211123303442031043123115213455152541435522242266645543311341343514541312331003041323121132333230
               001321210013322412003145152352533215251266535255436246225646232235155455213434510131104234003003222
               223300223021202423423443531414132551356424245326334266655533565213421321535315422410221142133031312
               222100212043241001125454253523452134533223365232264243252423323432612224141255512031323011313003103
               021121103403121443434132421553436423352664246453242666446362544432226535142445142544021324240001211
               133332012412432333133345415214366446545565543462332546664335363355632655443241344522421402100130202
               203311422144324333445534552346564665545464453356346434642563543262655253451122554135410444243311311
               322001144142332343512222522324564232262466666644733543366455434326365635422423142542532442322034233
               101313214102402432135211163436536554325523756753777636774442234646434532462532323552412110323232323
               001234101142053124341452526452436566623744636336343435367647546635623353553555534224231314214100212
               011340142113121313424442565642324364776443464557637463364534454646665424552553413532212514302012341
               131222200221422152343654426266433667545366347373774775466374344436764353332356464535514434324340110
               111003023342553555436244262523625335453474533637454347543437747544744452225334642442335223143444444
               321342121452254343244365542366665534457746575473573344454777777733474472444242626215251353540020101
               112143303315213335256234665554477437377636773577545734476555474364535534426563254464121321342443010
               234221035235112225663223532437466537576546445657685585555367446553357466742263556432335535233141333
               222423221324421313653564633554665443633556667677566864675574576677657463472426635262534215124024221
               324331152222354262443463537535377475436657674545875474876844577557734435563264343363652233415524410
               213342122353141336533663276453775344477647556654774756865757686866756357447742256452564542442334022
               031310253121515643256663543343774374464475868668856447745665786466775333667666543356266334544230111
               003414153141543253444633475333643566678746567545465484644456865746567655663777455623456224131310102
               330142523253452632255556734446376546656754857454455688868674648457646443377555553462454615251453442
               133235545322333532564577755447648846778644645755887995696448544647577553657663736532335515233432204
               334335524535365552354756376746487575645445659755695765666787544578886583475443775453346362431143131
               341031551123323343266454736348684858484889599858696567585876875856777548475375347666344435552535130
               330124132352653624374474564584845574667585775689557998777788587447485878554574675446242255332135430
               022332341314322336445375435566746866478875855558865695755978785575785875787557756366564323334355421
               422512114322233253336344454648844756687768565958568995898568666686676864785573463376463556254314532
               103452142442634255644464378667858765668577695986766569979865855566666464448666555466453356541352555
               241112442533463566644466384475785778588865998998987687878685758585665474766754566466245444341351111
               242331514626445247647536577786867686956757887867997868868565789798577586688475374636425346525131421
               243314253655642337357775774485658855797598667666899989967796597576898668686444477753324232232351154
               141212322533446654653434488448555885596757899977797669696788657787555457687585673655426445422433424
               342122455562335353366637887665858986966688896796677969888697689675958786488564747444335354224244114
               312551335256534266765767457478789957996778878677968997797796765897866875565887677747646635544512542
               211534134626623545346577744877677876857796986969678796966776968959655984565744445456734645346232512
               032231543234465677337374577887787968857667678778778977789989896785756795858858774466662336446214512
               324534154425536743565446458446858967886787686879898778778766688758858698485648874353672463266531234
               435134112333555443375367474578758968688997879997977798878668997879689696565868444476554256336155424
               415141242653322635673376487876767958867777897899878989988966688858578595645554834563765634653311443
               333153263566332363556565647675776889997977998798979989789779878767599668677764763567736353533453222
               324533443324323546474686475757577689889799697979879899988679887776859589545565675357363456543514214
               541212526226223464775746748666766598968767978778799799798889796767886998655687843577354334262542222
               254152122622335364377555764858997897878877898777778878987979889696555579776547754776646635325242443
               233322465646563366564454647465965779689999699998999997777987688766899995784766835357675264423235125
               535451544662624547343458788846585555988689797879987987777878766896757589544864553377356554234332115
               244155565332636373565485774649696779997697667779778999898989987886985757674786675646374345632323155
               541445123454425766545765876866698666699986888787877988888968997758669898748856774455664363534535245
               455333345362435745575658765456987788888989966788777799798897779657686755844678835677774462453432523
               221534413542463536734757867768888675877686879688897878998879669688977768885557754643352225526224333
               221234516433463336765777686785786979666796878779797869789769666688997978844648636667643543232444133
               332215426646234434537648668445869988799778678686878967687668768657879955656846347743656535222133422
               031553553463354667655574475474488786777686996886779777887886867775767548458866434657666522253512145
               331532316364356445453744448764666787785586687678778969697798769655779847465578374664546445324424334
               231442532632664337564767657644566986698596889799897977969897769799988688455757473356424325365512353
               411553532344436334565736755688777565758689667688779789696666578858657666568855774576363253255223545
               142243141626243333657765468644745995798775976978979869767759758699574774455763666347225335254143154
               434542532345526233743743645456757487695765886898969876785665857797756868557564677455336336551245453
               242111213336234342634744347655654759597665895975786988997859879785674785466364664476642426523334413
               240432541545646555637655375878747648689956699797668787668987857785877748855353544573343263423351451
               233353541126663465447637576756467765799985779857685597778958855944587564657567344425653566513324251
               202133125415524635556354365368576748667886559966988689898777998784678575655573455765246634331111323
               214241132111456463244677753468567888865459986568778556756857554488444844574373477643253433514113414
               404043331523632565246656343354566578548867897777799576599855576486586885647776646654645643421251434
               321431341441524644343774675764678486487885575766685789987548644455655465455574763334325643223532112
               312044135522435234264466734356344664746887668755765684564685544655877565747654532452656321312254240
               221443411432246553232444575537753577667488768677578665484847677754883754573376245624364553144524310
               333011355143132662522563635437666768647485448557477746476767655566765543476573262345334242422532040
               130221313353423632635345377635457345475858877766844478544578458753453567556654264564563111241221424
               420000412311333223455353344535675346674758458678588744648777864536643447536366432335235213533510131
               033211203422344216654546246576546347545346767685845774858574755653734736666352442234431433312122020
               420112221555544554533234236234734667465467566545488844644654555366376734646542254343325451511102040
               120131424153254535662246332535575674733567577374535555633745576736633645534235222461444514331311422
               213413130454123421355422255452563464655444643735457443555675634373377635255435222352513552212031020
               003020114244244432233353656455567667744454636765475774754663376447476625234623436352314413331402042
               324044223442544345113455525643466577777675355766774773637445365463522332325642562435514442142404141
               210441410124444533445153545634656623447563677736473335673574337754666335462353221213524122021303233
               220001402334314112552325546465543662455673446333744466773363533553456364646542224332314211402042433
               023121402021311422535432445424233646345224547753633435644474236362532444465321253221141144332413310
               323020230034123314134444533446535564436335465334366443566523243354226225646253351453120014034104303
               312321342442214055412542111145545344545643544554353232456465256462323445343525123442530123340220021
               123101342024104423223521423214332556563335635536633462555625643665222463242344244522332431230422020
               300202224124443140142225324453536234525546225346366434644466234244662333545341335424243412214111222
               230103311300324320142422532144424144356464635656343323243433326662623345523535351332140310322302320
               102213121041413130343312325141341341255366646363663225633336454234535134543235451400033131103312302
               220010012323010223213414114534555442121353366562642344263362424334153543414344531223433101022121003
               111303131132410340323210521254433532435211213444624335211322534254443353121223030123212013112122222
               221300032203340322340214302442511523252243154411411131132224142542512334245223323243012130220310111
               210202131021310312212042132343151311311552432124522351251323142332524114321412240003424203112223000
               120001230322001230123443443202113524332314432515141344214224414354425231021342403441230201112313220
               000101310102010302144343300032331251425155511533145124553435221424141440223210230440103301210001022
               111021010130123300013234210112002104312354542225421421441334253132031032244142134422332001101011222"""
}
