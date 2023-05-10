import 'package:flutter/material.dart';

class CriarNFSE extends StatefulWidget {
  const CriarNFSE({super.key});

  @override
  CriarNFSEState createState() => CriarNFSEState();
}

class CriarNFSEState extends State<CriarNFSE> {
  final _formKey = GlobalKey<FormState>();
  String _numeroNotaFiscal = "";
  double _valorTotal = 0;
  String _razaoSocial = "";
  String _inscricaoMunicipal = "";
  DateTime _data = DateTime(0);
  bool _pessoaJuridica = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Criar Nota Fiscal'),
        centerTitle: true,
      ),
      body: SingleChildScrollView(
        child: Container(
          padding: const EdgeInsets.all(16.0),
          child: Form(
            key: _formKey,
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                TextFormField(
                  decoration: const InputDecoration(
                    labelText: 'Número da Nota Fiscal',
                  ),
                  validator: (value) {
                    if (value!.isEmpty) {
                      return 'Por favor, informe o número da nota fiscal';
                    }
                    return null;
                  },
                  onSaved: (value) => _numeroNotaFiscal = value!,
                ),
                const SizedBox(height: 16.0),
                TextFormField(
                  decoration: const InputDecoration(
                    labelText: 'Data',
                  ),
                  onTap: () async {
                    DateTime? picked = await showDatePicker(
                      context: context,
                      initialDate: DateTime.now(),
                      firstDate: DateTime(2000),
                      lastDate: DateTime.now(),
                    );
                    if (picked != null && picked != _data) {
                      setState(() {
                        _data = picked;
                      });
                    }
                  },
                  readOnly: true,
                  validator: (value) {
                    if (_data == null) {
                      return 'Por favor, informe a data da nota fiscal';
                    }
                    return null;
                  },
                  controller: TextEditingController(
                    text: _data == null
                        ? ''
                        : '${_data.day}/${_data.month}/${_data.year}',
                  ),
                  onSaved: (value) {
                    if (_data != null) {
                      setState(() {
                        _data = DateTime(_data.year, _data.month, _data.day);
                      });
                    }
                  },
                ),
                const SizedBox(height: 16.0),
                TextFormField(
                  decoration: const InputDecoration(
                    labelText: 'Valor Total',
                  ),
                  keyboardType: TextInputType.number,
                  validator: (value) {
                    if (value!.isEmpty) {
                      return 'Por favor, informe o valor total da nota fiscal';
                    }
                    return null;
                  },
                  onSaved: (value) => _valorTotal = double.parse(value!),
                ),
                const SizedBox(height: 16.0),
                Row(
                  children: [
                    const Text(
                      'Pessoa Física/Jurídica:',
                      style: TextStyle(fontSize: 16.0),
                    ),
                    const SizedBox(width: 8.0),
                    Switch(
                      value: _pessoaJuridica,
                      onChanged: (value) {
                        setState(() {
                          _pessoaJuridica = value;
                        });
                      },
                    ),
                    const SizedBox(width: 8.0),
                    Text(
                      _pessoaJuridica ? 'Jurídica' : 'Física',
                      style: const TextStyle(fontSize: 16.0),
                    ),
                  ],
                ),
                if (_pessoaJuridica)
                  TextFormField(
                    decoration: const InputDecoration(
                      labelText: 'Razão Social',
                    ),
                    validator: (value) {
                      if (value!.isEmpty) {
                        return 'Por favor, informe a razão social';
                      }
                      return null;
                    },
                    onSaved: (value) => _razaoSocial = value!,
                  ),
                if (_pessoaJuridica) const SizedBox(height: 16.0),
                TextFormField(
                  decoration: const InputDecoration(
                    labelText: 'Inscrição Municipal',
                  ),
                  validator: (value) {
                    if (value!.isEmpty) {
                      return 'Por favor, informe a inscrição municipal';
                    }
                    return null;
                  },
                  onSaved: (value) => _inscricaoMunicipal = value!,
                ),
                const SizedBox(height: 16.0),
                ElevatedButton(
                  child: const Text('Salvar'),
                  onPressed: () {
                    if (_formKey.currentState!.validate()) {
                      _formKey.currentState!.save();
                      Navigator.pop(context);
                    }
                  },
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
