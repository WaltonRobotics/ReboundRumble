<?xml version='1.0' encoding='UTF-8'?>
<Project Type="Project" LVVersion="11008008">
	<Item Name="My Computer" Type="My Computer">
		<Property Name="NI.SortType" Type="Int">3</Property>
		<Property Name="server.app.propertiesEnabled" Type="Bool">true</Property>
		<Property Name="server.control.propertiesEnabled" Type="Bool">true</Property>
		<Property Name="server.tcp.enabled" Type="Bool">false</Property>
		<Property Name="server.tcp.port" Type="Int">0</Property>
		<Property Name="server.tcp.serviceName" Type="Str">My Computer/VI Server</Property>
		<Property Name="server.tcp.serviceName.default" Type="Str">My Computer/VI Server</Property>
		<Property Name="server.vi.callsEnabled" Type="Bool">true</Property>
		<Property Name="server.vi.propertiesEnabled" Type="Bool">true</Property>
		<Property Name="specify.custom.address" Type="Bool">false</Property>
		<Item Name="Support" Type="Folder">
			<Item Name="Parse Digital Module.vi" Type="VI" URL="../Parse Digital Module.vi"/>
			<Item Name="Update Battery Indicator.vi" Type="VI" URL="../Update Battery Indicator.vi"/>
			<Item Name="Save DB Images.vi" Type="VI" URL="../Save DB Images.vi"/>
			<Item Name="Receive DS Packet.vi" Type="VI" URL="../Receive DS Packet.vi"/>
			<Item Name="App EXE.ico" Type="Document" URL="../App EXE.ico"/>
			<Item Name="Decode Status Byte.vi" Type="VI" URL="../Decode Status Byte.vi"/>
			<Item Name="Elapsed Time.vi" Type="VI" URL="../Elapsed Time.vi"/>
		</Item>
		<Item Name="TypeDefs" Type="Folder">
			<Item Name="DStoPCPacketTypeDef.ctl" Type="VI" URL="../DStoPCPacketTypeDef.ctl"/>
		</Item>
		<Item Name="Dashboard Main.vi" Type="VI" URL="../Dashboard Main.vi"/>
		<Item Name="Build Simple Skeleton Data.vi" Type="VI" URL="../Build Simple Skeleton Data.vi"/>
		<Item Name="Build Camera IP.vi" Type="VI" URL="../Build Camera IP.vi"/>
		<Item Name="Draw Simple Skeleton Picture.vi" Type="VI" URL="../Draw Simple Skeleton Picture.vi"/>
		<Item Name="Dependencies" Type="Dependencies">
			<Item Name="vi.lib" Type="Folder">
				<Item Name="IMAQ Create" Type="VI" URL="/&lt;vilib&gt;/vision/Basics.llb/IMAQ Create"/>
				<Item Name="IMAQ Image.ctl" Type="VI" URL="/&lt;vilib&gt;/vision/Image Controls.llb/IMAQ Image.ctl"/>
				<Item Name="Image Type" Type="VI" URL="/&lt;vilib&gt;/vision/Image Controls.llb/Image Type"/>
				<Item Name="Clear Errors.vi" Type="VI" URL="/&lt;vilib&gt;/Utility/error.llb/Clear Errors.vi"/>
				<Item Name="IMAQ Write File 2" Type="VI" URL="/&lt;vilib&gt;/vision/Files.llb/IMAQ Write File 2"/>
				<Item Name="IMAQ Write BMP File 2" Type="VI" URL="/&lt;vilib&gt;/vision/Files.llb/IMAQ Write BMP File 2"/>
				<Item Name="IMAQ Write Image And Vision Info File 2" Type="VI" URL="/&lt;vilib&gt;/vision/Files.llb/IMAQ Write Image And Vision Info File 2"/>
				<Item Name="IMAQ Write JPEG File 2" Type="VI" URL="/&lt;vilib&gt;/vision/Files.llb/IMAQ Write JPEG File 2"/>
				<Item Name="IMAQ Write JPEG2000 File 2" Type="VI" URL="/&lt;vilib&gt;/vision/Files.llb/IMAQ Write JPEG2000 File 2"/>
				<Item Name="IMAQ Write PNG File 2" Type="VI" URL="/&lt;vilib&gt;/vision/Files.llb/IMAQ Write PNG File 2"/>
				<Item Name="IMAQ Write TIFF File 2" Type="VI" URL="/&lt;vilib&gt;/vision/Files.llb/IMAQ Write TIFF File 2"/>
				<Item Name="Dflt Data Dir.vi" Type="VI" URL="/&lt;vilib&gt;/Utility/file.llb/Dflt Data Dir.vi"/>
				<Item Name="WPI_DriverStationDigitalData.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/DriverStation/WPI_DriverStationDigitalData.ctl"/>
				<Item Name="IMAQ Clear Overlay" Type="VI" URL="/&lt;vilib&gt;/vision/Overlay.llb/IMAQ Clear Overlay"/>
				<Item Name="WPI_JoystickDeviceDataRaw.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Joystick/WPI_JoystickDeviceDataRaw.ctl"/>
				<Item Name="WPI_CameraDecodeJPEGString.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraDecodeJPEGString.vi"/>
				<Item Name="SkeletonTrackState.ctl" Type="VI" URL="/&lt;vilib&gt;/addons/Kinect/SkeletonTrackState.ctl"/>
				<Item Name="Vector4.ctl" Type="VI" URL="/&lt;vilib&gt;/addons/Kinect/Vector4.ctl"/>
				<Item Name="WPI_CameraGet Image From Controller.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Image From Controller.vi"/>
				<Item Name="WPI_CameraGet Image From TCP.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Image From TCP.vi"/>
				<Item Name="WPI_CameraERRBadImageData.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraERRBadImageData.vi"/>
				<Item Name="WPI_CameraGet Image From TCP 1.0.0.0.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Image From TCP 1.0.0.0.vi"/>
				<Item Name="SkeletonVertexTrackState.ctl" Type="VI" URL="/&lt;vilib&gt;/addons/Kinect/SkeletonVertexTrackState.ctl"/>
				<Item Name="LVBoundsTypeDef.ctl" Type="VI" URL="/&lt;vilib&gt;/Utility/miscctls.llb/LVBoundsTypeDef.ctl"/>
				<Item Name="Map To Depth Space.vi" Type="VI" URL="/&lt;vilib&gt;/addons/Kinect/Map To Depth Space.vi"/>
				<Item Name="Scale Skeleton Points to Display.vi" Type="VI" URL="/&lt;vilib&gt;/addons/Kinect/Scale Skeleton Points to Display.vi"/>
				<Item Name="Make Skeleton Connectors.vi" Type="VI" URL="/&lt;vilib&gt;/addons/Kinect/Make Skeleton Connectors.vi"/>
				<Item Name="Draw Multiple Lines.vi" Type="VI" URL="/&lt;vilib&gt;/picture/picture.llb/Draw Multiple Lines.vi"/>
				<Item Name="Set Pen State.vi" Type="VI" URL="/&lt;vilib&gt;/picture/picture.llb/Set Pen State.vi"/>
				<Item Name="Hilite Color.vi" Type="VI" URL="/&lt;vilib&gt;/picture/pictutil.llb/Hilite Color.vi"/>
				<Item Name="Draw Circle by Radius.vi" Type="VI" URL="/&lt;vilib&gt;/picture/pictutil.llb/Draw Circle by Radius.vi"/>
				<Item Name="Draw Arc.vi" Type="VI" URL="/&lt;vilib&gt;/picture/picture.llb/Draw Arc.vi"/>
				<Item Name="WPI_JoystickScaleTo100PlusMinRange.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Joystick/WPI_JoystickScaleTo100PlusMinRange.vi"/>
				<Item Name="WPI_CameraRead MJPG.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraRead MJPG.vi"/>
				<Item Name="WPI_CameraIssue HTTP Request with Authentication.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraIssue HTTP Request with Authentication.vi"/>
				<Item Name="Trim Whitespace.vi" Type="VI" URL="/&lt;vilib&gt;/Utility/error.llb/Trim Whitespace.vi"/>
				<Item Name="whitespace.ctl" Type="VI" URL="/&lt;vilib&gt;/Utility/error.llb/whitespace.ctl"/>
				<Item Name="WPI_CameraERRAccountProblem.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraERRAccountProblem.vi"/>
				<Item Name="WPI_UtilitiesFRC Build Error.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Utilities/WPI_UtilitiesFRC Build Error.vi"/>
				<Item Name="WPI_CameraERRFailedComm.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraERRFailedComm.vi"/>
				<Item Name="WPI_CameraImageSize.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraImageSize.ctl"/>
				<Item Name="NI_Vision_Development_Module.lvlib" Type="Library" URL="/&lt;vilib&gt;/vision/NI_Vision_Development_Module.lvlib"/>
				<Item Name="IMAQ GetImageSize" Type="VI" URL="/&lt;vilib&gt;/vision/Basics.llb/IMAQ GetImageSize"/>
				<Item Name="Particle Parameters" Type="VI" URL="/&lt;vilib&gt;/vision/Image Controls.llb/Particle Parameters"/>
				<Item Name="Color to RGB.vi" Type="VI" URL="/&lt;vilib&gt;/Utility/colorconv.llb/Color to RGB.vi"/>
				<Item Name="IMAQ Overlay Text" Type="VI" URL="/&lt;vilib&gt;/vision/Overlay.llb/IMAQ Overlay Text"/>
				<Item Name="IMAQ Overlay Rectangle" Type="VI" URL="/&lt;vilib&gt;/vision/Overlay.llb/IMAQ Overlay Rectangle"/>
				<Item Name="WPI_CameraSettings Control.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraSettings Control.ctl"/>
				<Item Name="WPI_CameraSettings Operations.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraSettings Operations.ctl"/>
				<Item Name="WPI_CameraManage Camera Settings.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraManage Camera Settings.vi"/>
				<Item Name="WPI_CameraIssue Get.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraIssue Get.vi"/>
				<Item Name="WPI_CameraGet Image Appearance Property.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Image Appearance Property.vi"/>
				<Item Name="WPI_CameraDevRef.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraDevRef.ctl"/>
				<Item Name="WPI_CameraGet Numeric Appearance Property.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Numeric Appearance Property.vi"/>
				<Item Name="WPI_CameraGet Image Compression.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Image Compression.vi"/>
				<Item Name="WPI_CameraGet Enum Sensor Property.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Enum Sensor Property.vi"/>
				<Item Name="WPI_CameraGet White Balance Values.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet White Balance Values.vi"/>
				<Item Name="WPI_CameraWhite Balance Values.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraWhite Balance Values.ctl"/>
				<Item Name="WPI_CameraGet White Balance.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet White Balance.vi"/>
				<Item Name="WPI_CameraGet Numeric Sensor Property.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Numeric Sensor Property.vi"/>
				<Item Name="WPI_CameraGet Sharpness.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Sharpness.vi"/>
				<Item Name="WPI_CameraGet Color Level.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Color Level.vi"/>
				<Item Name="WPI_CameraGet Enum Appearance Property.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Enum Appearance Property.vi"/>
				<Item Name="WPI_CameraGet Image Size Values.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Image Size Values.vi"/>
				<Item Name="WPI_CameraGet Image Size.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Image Size.vi"/>
				<Item Name="WPI_CameraGet Brightness.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Brightness.vi"/>
				<Item Name="WPI_CameraGet Exposure Values.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Exposure Values.vi"/>
				<Item Name="WPI_CameraExposure Values.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraExposure Values.ctl"/>
				<Item Name="WPI_CameraGet Exposure.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Exposure.vi"/>
				<Item Name="WPI_CameraGet Exposure Priority Values.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Exposure Priority Values.vi"/>
				<Item Name="WPI_CameraExposure Priority Values.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraExposure Priority Values.ctl"/>
				<Item Name="WPI_CameraGet Exposure Priority.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Exposure Priority.vi"/>
				<Item Name="WPI_CameraGet Color Enable.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Color Enable.vi"/>
				<Item Name="WPI_CameraGet Acquire Image Notifier Internal.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraGet Acquire Image Notifier Internal.vi"/>
				<Item Name="WPI_CameraERRAlreadyOpened.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraERRAlreadyOpened.vi"/>
				<Item Name="WPI_CameraRegistryActions.ctl" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraRegistryActions.ctl"/>
				<Item Name="WPI_Camera Registry.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_Camera Registry.vi"/>
				<Item Name="WPI_CameraOpen.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraOpen.vi"/>
				<Item Name="WPI_CameraSet Image Appearance Property.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraSet Image Appearance Property.vi"/>
				<Item Name="WPI_CameraSet Color Enable.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraSet Color Enable.vi"/>
				<Item Name="WPI_CameraSet Enum Sensor Property.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraSet Enum Sensor Property.vi"/>
				<Item Name="WPI_CameraSet White Balance.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraSet White Balance.vi"/>
				<Item Name="WPI_CameraSet Numeric Sensor Property.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraSet Numeric Sensor Property.vi"/>
				<Item Name="WPI_CameraSet Brightness.vi" Type="VI" URL="/&lt;vilib&gt;/Rock Robotics/WPI/Camera/WPI_CameraSet Brightness.vi"/>
			</Item>
			<Item Name="nivissvc.dll" Type="Document" URL="nivissvc.dll">
				<Property Name="NI.PreserveRelativePath" Type="Bool">true</Property>
			</Item>
			<Item Name="nivision.dll" Type="Document" URL="nivision.dll">
				<Property Name="NI.PreserveRelativePath" Type="Bool">true</Property>
			</Item>
			<Item Name="Aspect Ratio Score.vi" Type="VI" URL="../../../../../../Program Files (x86)/National Instruments/LabVIEW 2011/examples/FRC/Vision/Rectangular Target Processing/Aspect Ratio Score.vi"/>
			<Item Name="Compute Distance.vi" Type="VI" URL="../../../../../../Program Files (x86)/National Instruments/LabVIEW 2011/examples/FRC/Vision/Rectangular Target Processing/Compute Distance.vi"/>
			<Item Name="Compute HSL Color Ranges.vi" Type="VI" URL="../../../../../../Program Files (x86)/National Instruments/LabVIEW 2011/examples/FRC/Vision/Rectangular Target Processing/Compute HSL Color Ranges.vi"/>
			<Item Name="Draw Filled Rect.vi" Type="VI" URL="../../../../../../Program Files (x86)/National Instruments/LabVIEW 2011/examples/FRC/Vision/Rectangular Target Processing/Draw Filled Rect.vi"/>
			<Item Name="Normalized Range Compare.vi" Type="VI" URL="../../../../../../Program Files (x86)/National Instruments/LabVIEW 2011/examples/FRC/Vision/Rectangular Target Processing/Normalized Range Compare.vi"/>
			<Item Name="Rectangularity Score.vi" Type="VI" URL="../../../../../../Program Files (x86)/National Instruments/LabVIEW 2011/examples/FRC/Vision/Rectangular Target Processing/Rectangularity Score.vi"/>
			<Item Name="Score Particles.vi" Type="VI" URL="../../../../../../Program Files (x86)/National Instruments/LabVIEW 2011/examples/FRC/Vision/Rectangular Target Processing/Score Particles.vi"/>
			<Item Name="Sorted Particle Report.vi" Type="VI" URL="../../../../../../Program Files (x86)/National Instruments/LabVIEW 2011/examples/FRC/Vision/Rectangular Target Processing/Sorted Particle Report.vi"/>
			<Item Name="Build NT Table ID Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Table ID Buffer.vi"/>
			<Item Name="Build NT Field ID Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Field ID Buffer.vi"/>
			<Item Name="Table ID.ctl" Type="VI" URL="../../Network Tables/Table ID.ctl"/>
			<Item Name="Field ID.ctl" Type="VI" URL="../../Network Tables/Field ID.ctl"/>
			<Item Name="Build NT Data Update Table.vi" Type="VI" URL="../../Network Tables/Build NT Data Update Table.vi"/>
			<Item Name="Build NT String Buffer.vi" Type="VI" URL="../../Network Tables/Build NT String Buffer.vi"/>
			<Item Name="Build NT Data Update String.vi" Type="VI" URL="../../Network Tables/Build NT Data Update String.vi"/>
			<Item Name="Build NT Dbl Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Dbl Buffer.vi"/>
			<Item Name="Build NT Data Update Dbl.vi" Type="VI" URL="../../Network Tables/Build NT Data Update Dbl.vi"/>
			<Item Name="Build NT Int Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Int Buffer.vi"/>
			<Item Name="Build NT Data Update Int.vi" Type="VI" URL="../../Network Tables/Build NT Data Update Int.vi"/>
			<Item Name="Build NT Data Update Boolean.vi" Type="VI" URL="../../Network Tables/Build NT Data Update Boolean.vi"/>
			<Item Name="Field Type.ctl" Type="VI" URL="../../Network Tables/Field Type.ctl"/>
			<Item Name="Field Data.ctl" Type="VI" URL="../../Network Tables/Field Data.ctl"/>
			<Item Name="Field Data Manager.vi" Type="VI" URL="../../Network Tables/Field Data Manager.vi"/>
			<Item Name="Build NT Table Field Assignment Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Table Field Assignment Buffer.vi"/>
			<Item Name="Create Field.vi" Type="VI" URL="../../Network Tables/Create Field.vi"/>
			<Item Name="Create Table.vi" Type="VI" URL="../../Network Tables/Create Table.vi"/>
			<Item Name="Make Table Operation.ctl" Type="VI" URL="../../Network Tables/Make Table Operation.ctl"/>
			<Item Name="Table Manager.vi" Type="VI" URL="../../Network Tables/Table Manager.vi"/>
			<Item Name="Produce Table Updates.vi" Type="VI" URL="../../Network Tables/Produce Table Updates.vi"/>
			<Item Name="Build NT Table Request Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Table Request Buffer.vi"/>
			<Item Name="Table Mapper.vi" Type="VI" URL="../../Network Tables/Table Mapper.vi"/>
			<Item Name="NT Globals.vi" Type="VI" URL="../../Network Tables/NT Globals.vi"/>
			<Item Name="Request NT Table.vi" Type="VI" URL="../../Network Tables/Request NT Table.vi"/>
			<Item Name="Build NT Data Update for Cluster.vi" Type="VI" URL="../../Network Tables/Build NT Data Update for Cluster.vi"/>
			<Item Name="Field Mapper.vi" Type="VI" URL="../../Network Tables/Field Mapper.vi"/>
			<Item Name="Manage Client Value Update.vi" Type="VI" URL="../../Network Tables/Manage Client Value Update.vi"/>
			<Item Name="Manage Confirmation Counts.vi" Type="VI" URL="../../Network Tables/Manage Confirmation Counts.vi"/>
			<Item Name="Build NT Ping Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Ping Buffer.vi"/>
			<Item Name="Build NT Table Assignment Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Table Assignment Buffer.vi"/>
			<Item Name="Manage Connection List.vi" Type="VI" URL="../../Network Tables/Manage Connection List.vi"/>
			<Item Name="Build NT Confirmation Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Confirmation Buffer.vi"/>
			<Item Name="Update Clients.vi" Type="VI" URL="../../Network Tables/Update Clients.vi"/>
			<Item Name="Build NT Denial Buffer.vi" Type="VI" URL="../../Network Tables/Build NT Denial Buffer.vi"/>
			<Item Name="Parse NT Table ID.vi" Type="VI" URL="../../Network Tables/Parse NT Table ID.vi"/>
			<Item Name="Parse NT Dbl.vi" Type="VI" URL="../../Network Tables/Parse NT Dbl.vi"/>
			<Item Name="Parse NT Int.vi" Type="VI" URL="../../Network Tables/Parse NT Int.vi"/>
			<Item Name="Parse NT String.vi" Type="VI" URL="../../Network Tables/Parse NT String.vi"/>
			<Item Name="Parse NT Field ID.vi" Type="VI" URL="../../Network Tables/Parse NT Field ID.vi"/>
			<Item Name="Handle Field Update.vi" Type="VI" URL="../../Network Tables/Handle Field Update.vi"/>
			<Item Name="Process one Action.vi" Type="VI" URL="../../Network Tables/Process one Action.vi"/>
			<Item Name="NT Client.vi" Type="VI" URL="../../Network Tables/NT Client.vi"/>
			<Item Name="Manage Dirty Field ID List.vi" Type="VI" URL="../../Network Tables/Manage Dirty Field ID List.vi"/>
			<Item Name="Write Value Core.vi" Type="VI" URL="../../Network Tables/Write Value Core.vi"/>
			<Item Name="NT Write Dbl.vi" Type="VI" URL="../../Network Tables/NT Write Dbl.vi"/>
		</Item>
		<Item Name="Build Specifications" Type="Build">
			<Item Name="FRC PC Dashboard" Type="EXE">
				<Property Name="App_INI_aliasGUID" Type="Str">{47BAFDCE-3F99-4134-9347-62A4C9A5434C}</Property>
				<Property Name="App_INI_GUID" Type="Str">{76D91052-50F0-4E0B-B76F-616DDC550CED}</Property>
				<Property Name="Bld_buildCacheID" Type="Str">{56AA9368-84D4-42E1-9CCF-4FA34A518587}</Property>
				<Property Name="Bld_buildSpecDescription" Type="Str">Build Dashboard Main.vi into an EXE that will respond to the driver station and display robot information on a PC.</Property>
				<Property Name="Bld_buildSpecName" Type="Str">FRC PC Dashboard</Property>
				<Property Name="Bld_excludeLibraryItems" Type="Bool">true</Property>
				<Property Name="Bld_excludePolymorphicVIs" Type="Bool">true</Property>
				<Property Name="Bld_localDestDir" Type="Path">../builds/FRC Dashboard Project/FRC PC Dashboard</Property>
				<Property Name="Bld_localDestDirType" Type="Str">relativeToCommon</Property>
				<Property Name="Bld_modifyLibraryFile" Type="Bool">true</Property>
				<Property Name="Bld_previewCacheID" Type="Str">{F12754D6-B5E0-496F-B50C-3EDB6F368199}</Property>
				<Property Name="Destination[0].destName" Type="Str">Dashboard.exe</Property>
				<Property Name="Destination[0].path" Type="Path">../builds/FRC Dashboard Project/FRC PC Dashboard/Dashboard.exe</Property>
				<Property Name="Destination[0].type" Type="Str">App</Property>
				<Property Name="Destination[1].destName" Type="Str">Support Directory</Property>
				<Property Name="Destination[1].path" Type="Path">../builds/FRC Dashboard Project/FRC PC Dashboard/data</Property>
				<Property Name="DestinationCount" Type="Int">2</Property>
				<Property Name="Exe_iconItemID" Type="Ref">/My Computer/Support/App EXE.ico</Property>
				<Property Name="Source[0].itemID" Type="Str">{F9D55EE4-BD0A-4E17-A96A-AE9A2ED0CC81}</Property>
				<Property Name="Source[0].type" Type="Str">Container</Property>
				<Property Name="Source[1].destinationIndex" Type="Int">0</Property>
				<Property Name="Source[1].itemID" Type="Ref">/My Computer/Dashboard Main.vi</Property>
				<Property Name="Source[1].sourceInclusion" Type="Str">TopLevel</Property>
				<Property Name="Source[1].type" Type="Str">VI</Property>
				<Property Name="SourceCount" Type="Int">2</Property>
				<Property Name="TgtF_fileDescription" Type="Str">FRC PC Dashboard</Property>
				<Property Name="TgtF_fileVersion.major" Type="Int">1</Property>
				<Property Name="TgtF_internalName" Type="Str">FRC PC Dashboard</Property>
				<Property Name="TgtF_targetfileGUID" Type="Str">{AEE2EF3D-7087-47D6-AEAE-9F87F896ED5E}</Property>
				<Property Name="TgtF_targetfileName" Type="Str">Dashboard.exe</Property>
			</Item>
			<Item Name="Dashboard Main" Type="EXE">
				<Property Name="App_copyErrors" Type="Bool">true</Property>
				<Property Name="App_INI_aliasGUID" Type="Str">{56061308-0784-426D-A520-A29377F83753}</Property>
				<Property Name="App_INI_GUID" Type="Str">{47E5B3FE-88AB-43BE-828D-F64EE6CCF985}</Property>
				<Property Name="Bld_buildCacheID" Type="Str">{AD58EAD3-927F-4877-80BC-47F32B4A5EC7}</Property>
				<Property Name="Bld_buildSpecName" Type="Str">Dashboard Main</Property>
				<Property Name="Bld_excludeLibraryItems" Type="Bool">true</Property>
				<Property Name="Bld_excludePolymorphicVIs" Type="Bool">true</Property>
				<Property Name="Bld_localDestDir" Type="Path">../builds/NI_AB_PROJECTNAME/Dashboard Main</Property>
				<Property Name="Bld_localDestDirType" Type="Str">relativeToCommon</Property>
				<Property Name="Bld_modifyLibraryFile" Type="Bool">true</Property>
				<Property Name="Bld_previewCacheID" Type="Str">{2260CB61-474F-438F-8478-2183F019037F}</Property>
				<Property Name="Destination[0].destName" Type="Str">ProcessDash.exe</Property>
				<Property Name="Destination[0].path" Type="Path">../builds/NI_AB_PROJECTNAME/Dashboard Main/ProcessDash.exe</Property>
				<Property Name="Destination[0].preserveHierarchy" Type="Bool">true</Property>
				<Property Name="Destination[0].type" Type="Str">App</Property>
				<Property Name="Destination[1].destName" Type="Str">Support Directory</Property>
				<Property Name="Destination[1].path" Type="Path">../builds/NI_AB_PROJECTNAME/Dashboard Main/data</Property>
				<Property Name="DestinationCount" Type="Int">2</Property>
				<Property Name="Source[0].itemID" Type="Str">{1292F339-ACD6-4A54-8573-7169A9C97086}</Property>
				<Property Name="Source[0].type" Type="Str">Container</Property>
				<Property Name="Source[1].destinationIndex" Type="Int">0</Property>
				<Property Name="Source[1].itemID" Type="Ref">/My Computer/Dashboard Main.vi</Property>
				<Property Name="Source[1].sourceInclusion" Type="Str">TopLevel</Property>
				<Property Name="Source[1].type" Type="Str">VI</Property>
				<Property Name="SourceCount" Type="Int">2</Property>
				<Property Name="TgtF_companyName" Type="Str">Walton Robotics</Property>
				<Property Name="TgtF_fileDescription" Type="Str">Dashboard Main</Property>
				<Property Name="TgtF_fileVersion.major" Type="Int">1</Property>
				<Property Name="TgtF_internalName" Type="Str">Dashboard Main</Property>
				<Property Name="TgtF_legalCopyright" Type="Str">Copyright © 2012 Walton Robotics</Property>
				<Property Name="TgtF_productName" Type="Str">Dashboard Main</Property>
				<Property Name="TgtF_targetfileGUID" Type="Str">{998478D1-EEB0-494C-8C68-0000A3107470}</Property>
				<Property Name="TgtF_targetfileName" Type="Str">ProcessDash.exe</Property>
			</Item>
		</Item>
	</Item>
</Project>
